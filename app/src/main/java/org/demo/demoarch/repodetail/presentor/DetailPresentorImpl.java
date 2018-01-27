package org.demo.demoarch.repodetail.presentor;

import org.demo.demoarch.core.cache.SubscriberDetail;
import org.demo.demoarch.core.network.RequestManager;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.di.scopes.ActivityScoped;
import org.demo.demoarch.di.scopes.FragmentScoped;
import org.demo.demoarch.repodetail.RepoDetailConvertor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by pagga9 on 1/27/2018.
 */
@ActivityScoped
public class DetailPresentorImpl implements DetailPresentor {

    private final RequestManager requestManager;
    private final RepoDetailConvertor detailConvertor;
    @Inject
    public DetailPresentorImpl(RequestManager manager,RepoDetailConvertor convertor){
        requestManager = manager;
        detailConvertor  =convertor;
    }
    @Override
    public Observable<RequestModel<List<SubscriberDetail>>> loadSubscriberList(long id) {
        return requestManager.getSubscriberForRepos(id,detailConvertor);
    }
}
