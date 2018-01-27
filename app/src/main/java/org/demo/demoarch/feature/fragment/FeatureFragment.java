package org.demo.demoarch.feature.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.demo.demoarch.ActivityUtils;
import org.demo.demoarch.R;
import org.demo.demoarch.common.ui.BaseFragment;
import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.network.RequestModel;
import org.demo.demoarch.di.scopes.ActivityScoped;
import org.demo.demoarch.feature.AdapterInteractor;
import org.demo.demoarch.feature.RepoAdapter;
import org.demo.demoarch.feature.presentor.FeaturePresentor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by pagga9 on 1/27/2018.
 */
@ActivityScoped
public class FeatureFragment extends BaseFragment implements AdapterInteractor {

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        adapter.setAdapterInteractor(this);
        recyclerView.setAdapter(adapter);
        if(presentor != null)
        presentor.loadList().observeOn(AndroidSchedulers.mainThread()).subscribe(model ->  {
                if(model.isProgres()){
                    progressBar.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    return;
                }else if(model.isSuccess() && model.getData() != null){
                    adapter.addData(model.getData());
                    recyclerView.setVisibility(View.VISIBLE);
                }else if(!TextUtils.isEmpty(model.getErrorMessage())){
                    Toast.makeText(getContext(),model.getErrorMessage(),Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.GONE);
            });
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
                Observable<RequestModel<List<RepoDetail>>> scrollObserver = presentor.scrollPosition(visibleItemCount,totalItemCount,firstVisibleItemPosition)
                  ;
                if(scrollObserver != null){
                   scrollObserver.observeOn(AndroidSchedulers.mainThread()).subscribe(model ->  {

                           if(model.isProgres()){
                               isLoading = true;
                           }else if(model.isSuccess() && model.getData() != null){
                               adapter.addData(model.getData());
                               isLoading = false;
                           }else if(!TextUtils.isEmpty(model.getErrorMessage())){
                               isLoading = false;
                           }

                       });
                }

            }
        }
    };


    @Override
    public void showDetail(RepoDetail repoDetail) {

        getActivity().startActivity(ActivityUtils.getDetailActivityIntent(repoDetail,getContext()));

    }
}
