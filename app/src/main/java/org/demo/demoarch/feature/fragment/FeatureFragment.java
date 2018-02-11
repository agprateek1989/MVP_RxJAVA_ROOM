package org.demo.demoarch.feature.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.demo.demoarch.R;
import org.demo.demoarch.common.ui.BaseFragment;
import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.di.scopes.ActivityScoped;
import org.demo.demoarch.feature.FeatureInteractor;
import org.demo.demoarch.feature.RepoAdapter;
import org.demo.demoarch.feature.presentor.FeaturePresentor;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by pagga9 on 1/27/2018.
 */
@ActivityScoped
public class FeatureFragment extends BaseFragment implements FeatureInteractor {

    @Inject
    FeaturePresentor presentor;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    @Inject
    RepoAdapter adapter ;
    private boolean isLoading;

    @Inject
    public FeatureFragment() {
        // Requires empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feature,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView)view.findViewById(R.id.repo_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnScrollListener(listener);
        recyclerView.setAdapter(adapter);
        presentor.attach(this);
        presentor.loadList();
    }

    private RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int firstVisibleItemPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).
                    findFirstVisibleItemPosition();
            if(!isLoading){
                presentor.scrollPosition(visibleItemCount,totalItemCount,firstVisibleItemPosition);
            }
        }
    };




    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData(List<RepoDetail> detail) {
        adapter.addData(detail);
    }

    @Override
    public void showList() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadingCompleted() {
        isLoading = false;
    }


}
