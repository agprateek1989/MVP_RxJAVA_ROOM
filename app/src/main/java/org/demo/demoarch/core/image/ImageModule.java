package org.demo.demoarch.core.image;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.demo.demoarch.core.network.NetworkModule;
import org.demo.demoarch.di.scopes.ActivityScoped;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Module(includes = NetworkModule.class)
public class ImageModule {

    @Provides
    public Picasso picasso(@ActivityScoped Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
