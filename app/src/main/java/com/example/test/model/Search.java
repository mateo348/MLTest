package com.example.test.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Search  {


    @SerializedName("results")
    private ArrayList<Result> results = null;

    public ArrayList<Result> getResults() {
        return results;
    }


}
