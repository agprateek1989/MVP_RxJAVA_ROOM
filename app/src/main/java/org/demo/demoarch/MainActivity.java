package org.demo.demoarch;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import org.demo.demoarch.feature.fragment.FeatureFragment;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<FeatureFragment> injectedFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FeatureFragment featureFragment = (FeatureFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (featureFragment == null) {
            featureFragment = injectedFragment.get();
           ActivityUtils.addFragment(getSupportFragmentManager(),featureFragment,R.id.content_frame);

        }
    }
}
