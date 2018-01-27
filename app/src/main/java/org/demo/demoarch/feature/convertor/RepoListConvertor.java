package org.demo.demoarch.feature.convertor;

import org.demo.demoarch.core.cache.RepoDetail;
import org.demo.demoarch.response.GitHubRepo;

import javax.inject.Inject;

/**
 * Convertor used for converting network response to model class required for view.
 * Created by pagga9 on 1/27/2018.
 */

public class RepoListConvertor {
    @Inject
    public RepoListConvertor(){

    }

    public RepoDetail convert(GitHubRepo repo){
        RepoDetail repoDetail = null;
        if(repo != null){
            repoDetail = new RepoDetail();
            repoDetail.setId(repo.getId());
            repoDetail.setTitle(repo.getName());
            repoDetail.setDescription(repo.getDesc());
            repoDetail.setOwnerLogin(repo.getOwner().getLogin());
            repoDetail.setOwnerURL(repo.getOwner().getUrl());
            repoDetail.setRepoUrl(repo.getUrl());
            repoDetail.setFullName(repo.getFullName());
            repoDetail.setForkCount(repo.getForkCount());
            repoDetail.setOwnerAvatar(repo.getOwner().getAvatar());
        }
        return repoDetail;
    }
}
