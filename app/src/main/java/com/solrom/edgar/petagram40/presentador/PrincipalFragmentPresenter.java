package com.solrom.edgar.petagram40.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.solrom.edgar.petagram40.bd.ConstructorMascotas;
import com.solrom.edgar.petagram40.fragment.IPrincipalFragment;
import com.solrom.edgar.petagram40.pojo.Mascota;
import com.solrom.edgar.petagram40.restApi.ConstantesRestApi;
import com.solrom.edgar.petagram40.restApi.EndpointsApi;
import com.solrom.edgar.petagram40.restApi.adapter.RestApiAdapter;
import com.solrom.edgar.petagram40.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by edgarsr on 18/10/17.
 */

public class PrincipalFragmentPresenter implements IPrincipalFragmentPresenter {

    private IPrincipalFragment iPrincipalFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    private String usuario = "";


    public PrincipalFragmentPresenter(IPrincipalFragment iPrincipalFragment, Context context) {
        this.iPrincipalFragment = iPrincipalFragment;
        this.context = context;
        obtenerMediosRecientes();
    }


    public PrincipalFragmentPresenter(IPrincipalFragment iPrincipalFragment, Context context, boolean flags) {
        this.iPrincipalFragment = iPrincipalFragment;
        this.context = context;
        obtenerMediosRecientesUsuario();
    }


    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasPrincipalFragment();
    }

    @Override
    public void eliminarUsuarioBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        constructorMascotas.eliminarUsuarioInstagram();
    }

    @Override
    public void obtenerUsuarioInstagramBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        usuario = constructorMascotas.obtenerUsuarioInstagram();
        //mostrarMascotasRecyclerView();
    }

    @Override
    public void mostrarMascotasPrincipalFragment() {
        iPrincipalFragment.crearAdaptador(mascotas);
    }


    @Override
    public void mostrarListaMascotasPrincipalFragment() {
        iPrincipalFragment.inicializarAdaptadorRV(iPrincipalFragment.crearAdaptadorListas(mascotas));
        iPrincipalFragment.generarGridLayout();
        iPrincipalFragment.actualizaPerfil();
    }

    @Override
    public void obtenerMediosRecientes() {
        //EJECUTAMOS LA CONEXION AL SERVIDOR

        //String url = ConstantesRestApi.ROOT_URL + ConstantesRestApi.KEY_GET_USER + ConstantesRestApi.usuario + ConstantesRestApi.KEY_ACCESS_TOKEN_SEARCH + ConstantesRestApi.ACCESS_TOKEN;
        String usuario = ConstantesRestApi.usuario;

        if (usuario != null) {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gsonUser = restApiAdapter.construyeGsonDeserializadorUser();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUser);
            //Call<MascotaResponse> mascotaResponseCall = endpointsApi.getUser(usuario);
            Call<MascotaResponse> mascotaResponseCall = endpointsApi.getUser(usuario);
            //Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();

            mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
                @Override
                public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                    MascotaResponse mascotaResponse = response.body();
                    mascotas = mascotaResponse.getMascotas();
                    mostrarMascotasPrincipalFragment();
                }

                @Override
                public void onFailure(Call<MascotaResponse> call, Throwable t) {
                    Toast.makeText(context, "Falló la conexión con Instagram. Intenta de nuevo.", Toast.LENGTH_LONG).show();
                    Log.e("FALLO EN LA CONEXION", t.toString());
                }
            });
        }
    }


    @Override
    public void obtenerMediosRecientesUsuario() {
        //EJECUTAMOS LA CONEXION AL SERVIDOR

        String id = ConstantesRestApi.idUsuario;

        if (id != null) {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gsonUser = restApiAdapter.construyeGsonDeserializadorMediaRecent();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUser);
            Call<MascotaResponse> mascotaResponseCall = endpointsApi.getMediaUser(id);

            mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
                @Override
                public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                    MascotaResponse mascotaResponse = response.body();
                    mascotas = mascotaResponse.getMascotas();
                    mostrarListaMascotasPrincipalFragment();
                }

                @Override
                public void onFailure(Call<MascotaResponse> call, Throwable t) {
                    Toast.makeText(context, "Falló la conexión con Instagram. Intenta de nuevo.", Toast.LENGTH_LONG).show();
                    Log.e("FALLO EN LA CONEXION", t.toString());
                }
            });
        }
    }
}
