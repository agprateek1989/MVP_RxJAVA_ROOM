package org.demo.demoarch.di;

import android.app.Application;

import org.demo.demoarch.DemoApplication;
import org.demo.demoarch.core.cache.CacheManager;
import org.demo.demoarch.core.image.ImageModule;
import org.demo.demoarch.core.network.NetworkModule;
import org.demo.demoarch.core.network.RequestManager;
import org.demo.demoarch.di.modules.ActivityModule;
import org.demo.demoarch.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Singleton
@Component(modules = {NetworkModule.class  , ImageModule.class,
        ActivityModule.class , ApplicationModule.class ,
        AndroidSupportInjectionModule.class})
public interface ApplicationComponent extends AndroidInjector<DemoApplication> {

    RequestManager requestManager();

    CacheManager cacheManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        ApplicationComponent.Builder application(Application application);

        ApplicationComponent build();
    }
}
