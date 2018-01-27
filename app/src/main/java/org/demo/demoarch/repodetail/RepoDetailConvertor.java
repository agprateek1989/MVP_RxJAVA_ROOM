package org.demo.demoarch.repodetail;

import org.demo.demoarch.core.cache.SubscriberDetail;
import org.demo.demoarch.response.OwnerResponse;

/**
 * Created by pagga9 on 1/27/2018.
 */

public class RepoDetailConvertor {

    public SubscriberDetail convert(OwnerResponse response,long id){

        SubscriberDetail detail = new SubscriberDetail();
        detail.setId(response.getId());
        detail.setAvatar(response.getAvatar());
        detail.setName(response.getLogin());
        detail.setRepoid(id);
        detail.setProfileUrl(response.getUrl());

        return detail;
    }
}
