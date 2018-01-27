package org.demo.demoarch.core.network;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.demo.demoarch.core.cache.CacheDAO;
import org.demo.demoarch.core.cache.CacheDB;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Module
abstract public class NetworkModule {

    private static final int SIXTY = 60;
    private static final int TIMEOUT_TIME_VALUE = 5 * SIXTY;
    private static final TimeUnit TIMEOUT_TIME_UNIT = SECONDS;




    @Provides
    @Singleton
    public static DemoArchService clientService(Retrofit retrofit) {
        return retrofit.create(DemoArchService.class);
    }


    @Singleton
    @Provides
    public static Gson gson() {
        return new Gson();
    }


    @Singleton
    @Provides
    static CacheDB provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), CacheDB.class, "RepoCache.db")
                .build();
    }

    @Singleton
    @Provides
    static CacheDAO provideCacheDao(CacheDB db) {
        return db.cacheDAO();
    }

    @Provides
    public static Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://api.github.com")
                .build();
    }


    @Provides
    @Singleton
    public static OkHttpClient okHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(null)
                .readTimeout(TIMEOUT_TIME_VALUE, TIMEOUT_TIME_UNIT)
                .connectTimeout(TIMEOUT_TIME_VALUE, TIMEOUT_TIME_UNIT)
                .writeTimeout(TIMEOUT_TIME_VALUE, TIMEOUT_TIME_UNIT);



        return builder.build();
    }


}

