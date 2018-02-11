package org.demo.demoarch.feature;

import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.network.RequestManager;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.feature.convertor.RepoListConvertor;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by praaggar1 on 2/11/2018.
 */

public class FeatureDomain {
    private final RequestManager manager;
    private RepoListConvertor convertor;
    @Inject
    FeatureDomain(RequestManager manager){
        this.manager = manager;
        this.convertor = new RepoListConvertor();
    }

    public ObservableTransformer applyScheduler(){

        return new ObservableTransformer(){

            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public Observable<RequestModel<List<RepoDetail>>> loadList(int page , int pageCount){
        return this.manager.requestData(page,pageCount,convertor).compose(applyScheduler());
    }
}
