package com.solrom.edgar.petagram40.restApi;

import com.solrom.edgar.petagram40.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by edgarsr on 16/10/17.
 */

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET("users/search?access_token=6216568161.c002059.032bb9dc8fe84b62ad3e36566348d490")
    Call<MascotaResponse> getUser(@Query("q") String username2);

    @GET(ConstantesRestApi.KEY_USERS + "{idUsuario}" +  ConstantesRestApi.KEY_GET_RECENT_MEDIA)
    Call<MascotaResponse> getMediaUser(@Path("idUsuario") String idUsuario2);
}
