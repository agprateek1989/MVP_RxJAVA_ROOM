package org.demo.demoarch.core.cache;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by pagga9 on 1/27/2018.
 */

@Database(entities = {RepoDetail.class,SubscriberDetail.class}, version = 1)
public abstract class CacheDB extends RoomDatabase {

    public abstract CacheDAO cacheDAO();
}