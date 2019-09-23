package com.example.test.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Representa el resultado obtenido en la busqueda de un determinado criterio
 * Se mapearon solo los atributos necesarios
 */
public class Search  {


    public Search(ArrayList<Result> results){
        this.results = results;
    }
    @SerializedName("results")
    private ArrayList<Result> results = null;

    public ArrayList<Result> getResults() {
        return results;
    }


}
