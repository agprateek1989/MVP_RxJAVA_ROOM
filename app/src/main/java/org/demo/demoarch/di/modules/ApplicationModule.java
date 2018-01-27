package org.demo.demoarch.di.modules;

/**
 * Created by pagga9 on 1/27/2018.
 */

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}
