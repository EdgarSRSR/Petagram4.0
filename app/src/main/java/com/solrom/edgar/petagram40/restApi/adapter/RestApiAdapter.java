package com.solrom.edgar.petagram40.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.solrom.edgar.petagram40.restApi.ConstantesRestApi;
import com.solrom.edgar.petagram40.restApi.EndpointsApi;
import com.solrom.edgar.petagram40.restApi.deserializador.MascotaDeserializador;
import com.solrom.edgar.petagram40.restApi.deserializador.MascotaDeserializadorUser;
import com.solrom.edgar.petagram40.restApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edgarsr on 16/10/17.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }




    public Gson construyeGsonDeserializadorMediaRecent()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class , new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUser()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class , new MascotaDeserializadorUser());
        return gsonBuilder.create();
    }
}
