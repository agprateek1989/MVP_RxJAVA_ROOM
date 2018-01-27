package org.demo.demoarch.feature.module;

import org.demo.demoarch.di.scopes.ActivityScoped;
import org.demo.demoarch.di.scopes.FragmentScoped;
import org.demo.demoarch.feature.fragment.FeatureFragment;
import org.demo.demoarch.feature.presentor.FeaturePresentor;
import org.demo.demoarch.feature.presentor.FeaturePresentorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Module
public abstract class FeatureModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract FeatureFragment featureFragment();

    @ActivityScoped
    @Binds
    abstract FeaturePresentor repoListPresentor(FeaturePresentorImpl presenter);

}
