package org.demo.demoarch;


import org.demo.demoarch.core.network.RequestManager;
import org.demo.demoarch.di.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class DemoApplication extends DaggerApplication {

    @Inject
    RequestManager requestManager;


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }
}
