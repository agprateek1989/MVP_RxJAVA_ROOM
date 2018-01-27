package org.demo.demoarch;

import android.os.Bundle;

import org.demo.demoarch.feature.fragment.FeatureFragment;
import org.demo.demoarch.repodetail.fragment.DetailFragment;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class DetailActivity extends DaggerAppCompatActivity {

    public static final String EXTRA_REPO_ID = "org.demo.demoarch.RepoID";
    public static final String EXTRA_REPO_NAME = "org.demo.demoarch.NAME";

    @Inject
    Lazy<DetailFragment> fragmentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (detailFragment == null) {
            detailFragment = fragmentProvider.get();
            ActivityUtils.addFragment(getSupportFragmentManager(),detailFragment,R.id.content_frame);

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
