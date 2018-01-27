package org.demo.demoarch.core.cache;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by pagga9 on 1/27/2018.
 */
@Singleton
public class CacheManager {

    private final CacheDAO mCacheDAO;
    @Inject
    CacheManager(CacheDAO cacheDAO){
        this.mCacheDAO = cacheDAO;
    }

    public RepoDetail getRepoDetail(long id){

        return this.mCacheDAO.getRepoDetail(id);
    }

    public void saveRepoDetail(RepoDetail repoDetail){


        this.mCacheDAO.insertRepo(repoDetail);

    }

    public List<RepoDetail> getCachedRepo(){

        return this.mCacheDAO.getRepoList();
    }

    public List<SubscriberDetail> getSubscriberForRepo(long repo){

        return this.mCacheDAO.getSubscriberForRepo(repo);
    }

    public void cacheSubscriberForRepo(SubscriberDetail detail){

        this.mCacheDAO.saveSubscriberForRepo(detail);
    }
}
