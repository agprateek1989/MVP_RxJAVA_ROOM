package org.demo.demoarch.repodetail.presentor;

import org.demo.demoarch.core.cache.SubscriberDetail;
import org.demo.demoarch.core.network.RequestModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by pagga9 on 1/27/2018.
 */

public interface DetailPresentor {

    Observable<RequestModel<List<SubscriberDetail>>> loadSubscriberList(long id);
}
