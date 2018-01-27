package org.demo.demoarch.detail;

import org.demo.demoarch.core.cache.SubscriberDetail;
import org.demo.demoarch.core.network.RequestManager;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.repodetail.RepoDetailConvertor;
import org.demo.demoarch.repodetail.presentor.DetailPresentor;
import org.demo.demoarch.repodetail.presentor.DetailPresentorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by pagga9 on 1/27/2018.
 */
@RunWith(PowerMockRunner.class)
public class DetailPresentorTest {

    @Mock
    RequestManager requestManager;

    private DetailPresentor presentor;

    @Mock
    private RepoDetailConvertor convertor;

    @Before
    public void setup(){
        presentor = new DetailPresentorImpl(requestManager,convertor);
    }

    @Test
    public void testLoadListProgress(){
        RequestModel<List<SubscriberDetail>> progress = RequestModel.isProgress();

        Mockito.when(requestManager.getSubscriberForRepos(1l,convertor))
                .thenReturn(io.reactivex.Observable.just(progress));
        RequestModel<List<SubscriberDetail>> response = presentor.loadSubscriberList(1l).blockingFirst();

        assertEquals(response.isProgres(),true);
    }

    @Test
    public void testLoadListData(){

        List<SubscriberDetail> list  = new ArrayList<>();
        SubscriberDetail detail = new SubscriberDetail();
        detail.setId(1);
        detail.setName("Abc");
        list.add(detail);
        RequestModel<List<SubscriberDetail>> data = RequestModel.isSuccess(list);
        Mockito.when(requestManager.getSubscriberForRepos(1l,convertor))
                .thenReturn(io.reactivex.Observable.just(data));
        RequestModel<List<SubscriberDetail>> response = presentor.loadSubscriberList(1l).blockingFirst();

        assertEquals(response.getData().size() == 1,true);
    }

    @Test
    public void testLoadListError(){


        RequestModel<List<SubscriberDetail>> data = RequestModel.error("No data available");
        Mockito.when(requestManager.getSubscriberForRepos(1l,convertor))
                .thenReturn(io.reactivex.Observable.just(data));

        RequestModel<List<SubscriberDetail>> response = presentor.loadSubscriberList(1l).blockingFirst();

        assertEquals(response.getErrorMessage(),"No data available");
    }

}
