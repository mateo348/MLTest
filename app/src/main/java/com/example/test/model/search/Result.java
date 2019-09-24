package com.example.test.model.search;

import com.example.test.util.AppUtils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Dentro de una busqueda {@linkplain Search}, es la cabecera de las publicaciones coindidentes
 * con el criterio de busqueda
 */
public class Result implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private Double price;
    @SerializedName("currency_id")
    private String currencyId;
    @SerializedName("condition")
    private String condition;
    @SerializedName("thumbnail")
    private String thumbnail;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFormatedPrice() {return AppUtils.getInstance().getCurrencySymbol(currencyId)+ AppUtils.getInstance().formatPrice(price.intValue()); }

    public String getConditionText() { return AppUtils.getInstance().getConditionText(condition);}

    public String getThumbnail() {
        return thumbnail;
    }

}
