package org.demo.demoarch.feature.presentor;

import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.feature.FeatureInteractor;

import java.util.List;

/**
 * Created by pagga9 on 1/27/2018.
 */

public interface FeaturePresentor {

    void loadList();
    void scrollPosition(int visibleCount, int total, int firstPosition);
    void attach(FeatureInteractor interactor);
}
