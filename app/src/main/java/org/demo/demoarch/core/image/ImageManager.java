package org.demo.demoarch.core.image;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Singleton
public class ImageManager {

    private final Picasso imageRequestor;
    @Inject
    public ImageManager(@Nonnull Picasso picasso){
        imageRequestor = picasso;
    }
    public void requestImage(@Nonnull ImageView view ,@Nonnull String url){

        imageRequestor.load(url).into(view);
    }
}
