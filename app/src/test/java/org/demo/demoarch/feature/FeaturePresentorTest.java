package org.demo.demoarch.feature;

import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.network.RequestManager;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.feature.convertor.RepoListConvertor;
import org.demo.demoarch.feature.presentor.FeaturePresentor;
import org.demo.demoarch.feature.presentor.FeaturePresentorImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by pagga9 on 1/27/2018.
 */
@RunWith(PowerMockRunner.class)
public class FeaturePresentorTest {

    @Mock
    RequestManager requestManager;

    private FeaturePresentor presentor;

    @Before
    public void setup(){

        presentor = new FeaturePresentorImpl(requestManager);
    }

    @Test
    public void testLoadListProgress(){
        RequestModel<List<RepoDetail>> progress = RequestModel.isProgress();
        RepoListConvertor convertor = new RepoListConvertor();
        Whitebox.setInternalState(presentor,"convertor",convertor);
        Mockito.when(requestManager.requestData(1,10,convertor))
                .thenReturn(io.reactivex.Observable.just(progress));


        RequestModel<List<RepoDetail>> response = presentor.loadList().blockingFirst();

        assertEquals(response.isProgres(),true);
    }

    @Test
    public void testLoadListData(){

        List<RepoDetail> list  = new ArrayList<>();
        RepoDetail detail = new RepoDetail();
        detail.setId(1);
        detail.setTitle("Abc");
        list.add(detail);
        RequestModel<List<RepoDetail>> data = RequestModel.isSuccess(list);
        RepoListConvertor convertor = new RepoListConvertor();
        Whitebox.setInternalState(presentor,"convertor",convertor);
        Mockito.when(requestManager.requestData(1,10,convertor))
                .thenReturn(io.reactivex.Observable.just(data));
        RequestModel<List<RepoDetail>> response = presentor.loadList().blockingFirst();

        assertEquals(response.getData().size() == 1,true);
    }

    @Test
    public void testLoadListError(){


        RequestModel<List<RepoDetail>> data = RequestModel.error("No data available");
        RepoListConvertor convertor = new RepoListConvertor();
        Whitebox.setInternalState(presentor,"convertor",convertor);
        Mockito.when(requestManager.requestData(1,10,convertor))
                .thenReturn(io.reactivex.Observable.just(data));

        RequestModel<List<RepoDetail>> response = presentor.loadList().blockingFirst();

        assertEquals(response.getErrorMessage(),"No data available");
    }

    @Test
    public void testScrollNoPageLoad(){


        presentor.scrollPosition(1,10,5);

        Mockito.verify(requestManager,Mockito.never()).requestData(1,10,new RepoListConvertor());

    }


    @Test
    public void testPageLoadProgress(){
        RequestModel<List<RepoDetail>> progress = RequestModel.isProgress();
        RepoListConvertor convertor = new RepoListConvertor();
        Whitebox.setInternalState(presentor,"convertor",convertor);
        Mockito.when(requestManager.requestData(3,10,convertor))
                .thenReturn(io.reactivex.Observable.just(progress));


        RequestModel<List<RepoDetail>> response = presentor.scrollPosition(20,20,1).blockingFirst();

        assertEquals(response.isProgres(),true);
    }

    @Test
    public void testPageLoadData(){

        List<RepoDetail> list  = new ArrayList<>();
        RepoDetail detail = new RepoDetail();
        detail.setId(1);
        detail.setTitle("Abc");
        list.add(detail);
        RequestModel<List<RepoDetail>> data = RequestModel.isSuccess(list);
        RepoListConvertor convertor = new RepoListConvertor();
        Whitebox.setInternalState(presentor,"convertor",convertor);
        Mockito.when(requestManager.requestData(3,10,convertor))
                .thenReturn(io.reactivex.Observable.just(data));
        RequestModel<List<RepoDetail>> response =presentor.scrollPosition(20,20,1).blockingFirst();

        assertEquals(response.getData().size() == 1,true);
    }

    @Test
    public void testNextPageLoadError(){


        RequestModel<List<RepoDetail>> data = RequestModel.error("No data available");
        RepoListConvertor convertor = new RepoListConvertor();
        Whitebox.setInternalState(presentor,"convertor",convertor);
        Mockito.when(requestManager.requestData(3,10,convertor))
                .thenReturn(io.reactivex.Observable.just(data));

        RequestModel<List<RepoDetail>> response = presentor.scrollPosition(20,20,1).blockingFirst();

        assertEquals(response.getErrorMessage(),"No data available");
    }

}
