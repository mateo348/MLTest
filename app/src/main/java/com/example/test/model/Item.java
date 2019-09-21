package com.example.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Clase para el mapeo de producto.
 * Se estan definiendo solo las propiedades que se desean obtener de la respuesta del API
 */
public class Item {

    final static String ID = "id";
    final static String TITLE = "title";
    final static String SUBTITLE = "subtitle";
    final static String PRICE = "price";
    final static String CATEGORY_ID = "category_id";
    final static String PICTURES = "pictures";
    final static String CURRENCY_ID = "currency_id";

    public static String ATTRIBUTES = String.format("{%1$s,%2$s,%3$s,%4$s,%5$s,%6$s,%7$s}", ID,TITLE,SUBTITLE,PRICE,CATEGORY_ID,PICTURES,CURRENCY_ID);


    @SerializedName(TITLE)
    private String title;
    @SerializedName(SUBTITLE)
    private String subtitle;
    @SerializedName(CATEGORY_ID)
    private String category_id;
    @SerializedName(PRICE)
    private double price;
    @SerializedName(PICTURES)
    private List<Picture> pictures;
    @SerializedName(CURRENCY_ID)
    private String currencyId;





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }



    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }



    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
}
