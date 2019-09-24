package com.example.test.model.item;

import com.example.test.util.AppUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Clase para el mapeo de producto.
 * Se estan definiendo solo las propiedades que se desean obtener de la respuesta del API
 */
public class Item {

    final static String ID = "id";
    final static String TITLE = "title";
    final static String PRICE = "price";
    final static String PICTURES = "pictures";
    final static String CURRENCY_ID = "currency_id";
    final static String ORIGINAL_PRICE = "original_price";

    public static String ATTRIBUTES = String.format("{%1$s,%2$s,%3$s,%4$s,%5$s,%6$s}", ID,TITLE,PRICE,PICTURES,CURRENCY_ID,ORIGINAL_PRICE);


    @SerializedName(ID)
    private String id;
    @SerializedName(TITLE)
    private String title;
    @SerializedName(PRICE)
    private Double price;
    @SerializedName(PICTURES)
    private List<Picture> pictures;
    @SerializedName(CURRENCY_ID)
    private String currencyId;


    public String getTitle() {
        return title;
    }

    public String getFormatedPrice() {
        return AppUtils.getInstance().getCurrencySymbol(currencyId)+ AppUtils.getInstance().formatPrice(price.intValue()); }

    public List<Picture> getPictures() {
        return pictures;
    }

}
