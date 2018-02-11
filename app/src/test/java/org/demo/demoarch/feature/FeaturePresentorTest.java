package org.demo.demoarch.feature;

import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.feature.presentor.FeaturePresentor;
import org.demo.demoarch.feature.presentor.FeaturePresentorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import io.reactivex.schedulers.TestScheduler;

/**
 * Created by pagga9 on 1/27/2018.
 */
@RunWith(PowerMockRunner.class)
public class FeaturePresentorTest {

    @Mock
    FeatureDomain domain;

    private FeaturePresentor presentor;
    @Mock
    FeatureInteractor interactor;

    @Before
    public void setup(){

        presentor = new FeaturePresentorImpl(domain);
        presentor.attach(interactor);
    }

    @Test
    public void testLoadListProgress(){
        RequestModel<List<RepoDetail>> progress = RequestModel.isProgress();
        Mockito.when(domain.loadList(1,10))
                .thenReturn(io.reactivex.Observable.just(progress).subscribeOn(new TestScheduler())
                        .observeOn(new TestScheduler()));


         presentor.loadList();

        Mockito.verify(interactor,Mockito.atLeastOnce()).showLoading();
        Mockito.verify(interactor,Mockito.atLeastOnce()).hideList();

    }


}
