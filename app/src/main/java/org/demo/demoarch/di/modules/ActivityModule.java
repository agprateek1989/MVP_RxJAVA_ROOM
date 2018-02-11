package org.demo.demoarch.di.modules;

import org.demo.demoarch.MainActivity;
import org.demo.demoarch.di.scopes.ActivityScoped;
import org.demo.demoarch.feature.module.FeatureModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Module
public abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = FeatureModule.class)
    abstract MainActivity mainActivity();

}
