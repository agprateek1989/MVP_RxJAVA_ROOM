package org.demo.demoarch.repodetail.module;

import org.demo.demoarch.DetailActivity;
import org.demo.demoarch.MainActivity;
import org.demo.demoarch.di.scopes.ActivityScoped;
import org.demo.demoarch.di.scopes.FragmentScoped;
import org.demo.demoarch.repodetail.RepoDetailConvertor;
import org.demo.demoarch.repodetail.fragment.DetailFragment;
import org.demo.demoarch.repodetail.presentor.DetailPresentor;
import org.demo.demoarch.repodetail.presentor.DetailPresentorImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Module
abstract public class DetailModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract DetailFragment detailFragment();

    @ActivityScoped
    @Binds
    abstract DetailPresentor detailPresentor(DetailPresentorImpl presenter);

    @Provides
    @ActivityScoped
    static String provideRepoName(DetailActivity activity) {
        return activity.getIntent().getStringExtra(DetailActivity.EXTRA_REPO_NAME);
    }

    @Provides
    @ActivityScoped
    static long provideRepoId(DetailActivity activity) {
        return activity.getIntent().getLongExtra(DetailActivity.EXTRA_REPO_ID,-1);
    }

    @Provides
    @ActivityScoped
    static RepoDetailConvertor provideRepoDetailConvertor(){
        return new RepoDetailConvertor();
    }
}
