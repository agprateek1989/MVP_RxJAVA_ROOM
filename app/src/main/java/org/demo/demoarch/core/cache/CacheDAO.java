package org.demo.demoarch.core.cache;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Dao
public interface CacheDAO {

    @Query("SELECT * FROM Repo WHERE entryid = :id")
    public RepoDetail getRepoDetail(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepo(RepoDetail detail);

    @Query("SELECT * FROM Repo")
    public List<RepoDetail> getRepoList();

    @Query("SELECT * FROM subscriber WHERE repoid = :repo")
    public List<SubscriberDetail> getSubscriberForRepo(long repo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSubscriberForRepo(SubscriberDetail detail);
}
