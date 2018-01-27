package org.demo.demoarch.feature.presentor;

import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.network.RequestManager;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.di.scopes.ActivityScoped;
import org.demo.demoarch.feature.convertor.RepoListConvertor;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pagga9 on 1/27/2018.
 */
@ActivityScoped
public class FeaturePresentorImpl implements FeaturePresentor {

    private final RequestManager manager;
    private final  RepoListConvertor convertor;
    private final int PAGE_SIZE = 10;

    @Inject
    public FeaturePresentorImpl(RequestManager requestManager ){
        this.manager = requestManager;
        this.convertor = new RepoListConvertor();
    }
    @Override
    public io.reactivex.Observable<RequestModel<List<RepoDetail>>> loadList(){

        return fetchRepo(1,10);
    }
    @Override
    public io.reactivex.Observable<RequestModel<List<RepoDetail>>> scrollPosition(int visibleCount, int total, int firstPosition){

            if ((visibleCount + firstPosition) >= total
                    && firstPosition >= 0
                    && total >= PAGE_SIZE) {

                int page = total/PAGE_SIZE;
                return loadNextPage(page+1);
            }


        return null;
    }

    private io.reactivex.Observable<RequestModel<List<RepoDetail>>> loadNextPage(int page){

        return fetchRepo(page,PAGE_SIZE);
    }

    private io.reactivex.Observable<RequestModel<List<RepoDetail>>> fetchRepo(int page , int count){
        return manager.requestData(page,count,convertor);
    }



}
