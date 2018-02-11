package org.demo.demoarch.feature.presentor;

import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.di.scopes.ActivityScoped;
import org.demo.demoarch.feature.FeatureDomain;
import org.demo.demoarch.feature.FeatureInteractor;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pagga9 on 1/27/2018.
 */
@ActivityScoped
public class FeaturePresentorImpl implements FeaturePresentor {


    private final int PAGE_SIZE = 10;

    private FeatureDomain featureDomain;
    private FeatureInteractor interactor;
    @Inject
    public FeaturePresentorImpl(FeatureDomain domain ){
       this.featureDomain = domain;
    }
    @Override
    public void loadList(){

        fetchRepo(1,10).subscribe(model -> {
                if(model.isProgres()){
                    if(interactor != null){
                        interactor.showLoading();
                        interactor.hideList();
                    }
                    return;
                }else if(model.isSuccess()){
                    if(interactor != null)
                        interactor.showData(model.getData());
                }else{
                    if(interactor != null)
                        interactor.showError(model.getErrorMessage());
                }
                interactor.hideLoading();
                interactor.showList();

        });
    }
    @Override
    public void scrollPosition(int visibleCount, int total, int firstPosition){

            if ((visibleCount + firstPosition) >= total
                    && firstPosition >= 0
                    && total >= PAGE_SIZE) {

                int page = total/PAGE_SIZE;
                loadNextPage(page+1).subscribe(model ->  {
                       if(model.isSuccess()){
                            if(interactor != null){
                                interactor.showData(model.getData());
                                interactor.loadingCompleted();
                            }
                        }else{
                            if(interactor != null){
                                interactor.showError(model.getErrorMessage());
                                interactor.loadingCompleted();
                            }
                        }
                });
            }

    }

    @Override
    public void attach(FeatureInteractor interactor) {
        this.interactor = interactor;
    }

    private io.reactivex.Observable<RequestModel<List<RepoDetail>>> loadNextPage(int page){

        return fetchRepo(page,PAGE_SIZE);
    }

    private io.reactivex.Observable<RequestModel<List<RepoDetail>>> fetchRepo(int page , int count){
        return featureDomain.loadList(page,count);
    }








}
