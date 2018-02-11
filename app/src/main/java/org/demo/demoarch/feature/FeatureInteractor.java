package org.demo.demoarch.feature;

import org.demo.demoarch.core.cache.RepoDetail;

import java.util.List;

/**
 * Created by praaggar1 on 2/11/2018.
 */

public interface FeatureInteractor {

    void showLoading();
    void hideLoading();
    void showData(List<RepoDetail> detail);
    void showList();
    void hideList();
    void showError(String message);
    void loadingCompleted();
}
