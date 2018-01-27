package org.demo.demoarch.repodetail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.demo.demoarch.R;
import org.demo.demoarch.core.cache.SubscriberDetail;
import org.demo.demoarch.core.image.ImageManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class SubscriberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubscriberDetail> source;
    private ImageManager manager;

    @Inject
    public SubscriberAdapter(ImageManager manager){
        this.source = new ArrayList<>();
        this.manager = manager;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.subscriber_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ItemHolder)holder).bindView(source.get(position));
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    public void addMoreData(List<SubscriberDetail> data){

        int previosu = source.size();
        if(data.size() > 0){
            source.addAll(data);
            notifyItemRangeChanged(previosu , previosu+data.size());
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder{

        private TextView ownerName;
        private ImageView avatar;
        public ItemHolder(View itemView) {
            super(itemView);
            avatar = (ImageView)itemView.findViewById(R.id.avatar_image);
            ownerName = (TextView)itemView.findViewById(R.id.owner_name);
        }

        public void bindView(SubscriberDetail detail){

            if(detail != null){

                manager.requestImage(avatar,detail.getAvatar());
                ownerName.setText(detail.getName());
            }
        }


    }

}
