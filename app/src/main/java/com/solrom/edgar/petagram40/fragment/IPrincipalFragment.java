package com.solrom.edgar.petagram40.fragment;

import com.solrom.edgar.petagram40.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram40.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by edgarsr on 18/10/17.
 */

public interface IPrincipalFragment {
    public void generarGridLayout();

    public MascotaAdaptador crearAdaptadorListas(ArrayList<Mascota> mascotas);

    public void crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

    public void actualizaPerfil();
}
