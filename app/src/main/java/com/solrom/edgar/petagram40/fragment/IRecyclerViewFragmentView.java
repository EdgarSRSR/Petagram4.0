package com.solrom.edgar.petagram40.fragment;

import com.solrom.edgar.petagram40.adapter.MascotaAdaptador;
import com.solrom.edgar.petagram40.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by edgarsr on 18/10/17.
 */

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();
    public void generarGridLayout();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
