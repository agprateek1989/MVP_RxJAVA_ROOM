package org.demo.demoarch.repodetail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.demo.demoarch.R;
import org.demo.demoarch.common.ui.BaseFragment;
import org.demo.demoarch.core.cache.SubscriberDetail;
import org.demo.demoarch.repodetail.adapter.SubscriberAdapter;
import org.demo.demoarch.repodetail.presentor.DetailPresentor;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class DetailFragment extends BaseFragment {

    @Inject
    DetailPresentor presentor;
    @Inject
    String repoName;

    @Inject
    long repoId;

    @Inject
    SubscriberAdapter adapter;
    private View container;
    private ProgressBar progressBar;
    private TextView count;

    @Inject
    public DetailFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView name = view.findViewById(R.id.name);
        container = view.findViewById(R.id.content_container);
        count = view.findViewById(R.id.count);
        progressBar = view.findViewById(R.id.progress_bar);
        RecyclerView recyclerView = view.findViewById(R.id.subscriber_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        name.setText(repoName);
        count.setText(adapter.getItemCount()+"");

        loadData();

    }

    private void loadData(){

        presentor.loadSubscriberList(repoId).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(listRequestModel -> {

                    if(listRequestModel.isProgres()){
                       showProgress();
                    }else if(listRequestModel.isSuccess() && listRequestModel.getData() != null){
                      updateData(listRequestModel.getData());
                    }else{
                        showError(listRequestModel.getErrorMessage());
                    }
                });
    }

    private void updateData(List<SubscriberDetail> list){

        progressBar.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
        adapter.addMoreData(list);
        count.setText(list.size()+"");
    }

    private void showError(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
        container.setVisibility(View.GONE);
    }

    private void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);
    }


}
