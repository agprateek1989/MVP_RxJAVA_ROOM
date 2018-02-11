package org.demo.demoarch.core.network;

import org.demo.demoarch.core.cache.CacheManager;
import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.feature.RepoRequest;
import org.demo.demoarch.feature.convertor.RepoListConvertor;
import org.demo.demoarch.response.GitHubRepo;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Singleton
public class RequestManager {

    private final DemoArchService service;
    private final CacheManager cacheManager;

    @Inject
    RequestManager(@Nonnull DemoArchService service, CacheManager manager){
        this.service = service;
        this.cacheManager = manager;
    }


    public Observable<RequestModel<List<RepoDetail>>> requestData(final int page , int count, @Nonnull final RepoListConvertor convertor){

        final RepoRequest repoRequest = new RepoRequest(page,count);

            RequestModel<List<RepoDetail>> progress = RequestModel.isProgress();
           return getRepoList(repoRequest,convertor).map(repoDetails -> RequestModel.isSuccess(repoDetails))
                   .onErrorReturn(throwable -> {
                       RequestModel<List<RepoDetail>> error = RequestModel.error("Unable to get the data " +
                           "due to "+throwable.getLocalizedMessage());
                           return error;
                   }).flatMap(model -> {
               if(!model.isSuccess())
                   return getDataFromDBInCaseOfError(repoRequest);
               else
                   return Observable.just(model);
           }).startWith(progress);

    }




    private Observable<RequestModel<List<RepoDetail>>> getDataFromDBInCaseOfError(RepoRequest request){
        return getCachedResult(request).flatMap(list -> Observable.just(RequestModel.isSuccess(list)));
    }
    private Observable<List<RepoDetail>> getCachedResult(RepoRequest repoRequest){

       return Observable.fromIterable(cacheManager.getCachedRepo()).
                skip(repoRequest.getPage()*repoRequest.getCount()).toList().toObservable();
    }

    //Fetch the data from network
    private Observable<List<RepoDetail>> getRepoList(@Nonnull final RepoRequest repoRequest, @Nonnull final RepoListConvertor convertor){

        return service.getRepoList(repoRequest.getPage(),repoRequest.getCount()).
                flatMap(response -> {if(response.isSuccessful()){
                    return saveRepoToDb(Observable.just(response.body()),convertor);
                }else{
                    return getCachedResult(repoRequest);
                }});

    }


    private Observable<List<RepoDetail>> saveRepoToDb(Observable<List<GitHubRepo>> observable , final RepoListConvertor convertor){
        return  observable.flatMap(gitHubRepos -> Observable.fromIterable(gitHubRepos)).map(repo -> {
                    RepoDetail detail = convertor.convert(repo);
                    cacheManager.saveRepoDetail(detail);
                    return detail;}).toList().toObservable();
    }




}
