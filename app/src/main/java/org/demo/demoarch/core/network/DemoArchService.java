package org.demo.demoarch.core.network;

import org.demo.demoarch.response.GitHubRepo;
import org.demo.demoarch.response.OwnerResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by pagga9 on 1/27/2018.
 */

public interface DemoArchService {

    @GET("repositories?since=364")
    Observable<Response<List<GitHubRepo>>> getRepoList(@Query("page")int page , @Query("per_page") int count);

    @GET("repos/{name}/subscribers")
    Observable<Response<List<OwnerResponse>>> getSubscriberList(@Path(encoded = true ,value = "name") String name);
}
