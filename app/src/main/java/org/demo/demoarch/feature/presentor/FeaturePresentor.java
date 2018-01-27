package org.demo.demoarch.feature.presentor;

import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.network.RequestModel;

import java.util.List;

/**
 * Created by pagga9 on 1/27/2018.
 */

public interface FeaturePresentor {

    io.reactivex.Observable<RequestModel<List<RepoDetail>>> loadList();
    io.reactivex.Observable<RequestModel<List<RepoDetail>>> scrollPosition(int visibleCount, int total, int firstPosition);
}
