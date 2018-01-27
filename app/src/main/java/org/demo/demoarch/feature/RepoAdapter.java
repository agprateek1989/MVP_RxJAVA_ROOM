package org.demo.demoarch.feature;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.demo.demoarch.R;
import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.core.image.ImageManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class RepoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RepoDetail> source;
    private AdapterInteractor interactor;
    private ImageManager mImageManager;

    @Inject
    public RepoAdapter(ImageManager manager) {
        this.source = new ArrayList<>();
        this.mImageManager = manager;
    }

    public void setAdapterInteractor(AdapterInteractor interactor){
        this.interactor = interactor;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ItemHolder) holder).bindView(source.get(position));
    }

    @Override
    public int getItemCount() {
        return this.source.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView forkCount;
        private TextView desc;
        private TextView ownerName;
        private RepoDetail detail;
        private ImageView avatar;
        public ItemHolder(View itemView) {
            super(itemView);
            avatar = (ImageView)itemView.findViewById(R.id.avatar_image);
            forkCount = itemView.findViewById(R.id.repo_fork_count);
            desc = (TextView)itemView.findViewById(R.id.repo_desc);
            ownerName = (TextView)itemView.findViewById(R.id.repo_owner);
            itemView.setOnClickListener(this);
        }

        public void bindView(RepoDetail detail){

            if(detail != null){

                String forkLabel = forkCount.getContext().getResources().
                        getString(R.string.forked_count_label,detail.getForkCount());
                mImageManager.requestImage(avatar,detail.getOwnerAvatar());
                this.detail = detail;
                forkCount.setText(forkLabel);
                desc.setText(detail.getDescription());
                ownerName.setText(detail.getOwnerLogin());
            }
        }

        @Override
        public void onClick(View view) {
            if(interactor != null){
                interactor.showDetail(detail);
            }
        }
    }

    public void addData(List<RepoDetail> data){

        int previosu = source.size();
        if(data.size() > 0){
            source.addAll(data);
            notifyItemRangeChanged(previosu , previosu+data.size()-1);
        }
    }
}
