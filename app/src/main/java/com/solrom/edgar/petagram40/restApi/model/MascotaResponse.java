package com.solrom.edgar.petagram40.restApi.model;

import com.solrom.edgar.petagram40.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by edgarsr on 16/10/17.
 */

public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }



}
