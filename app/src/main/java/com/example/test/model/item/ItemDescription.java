package com.example.test.model.item;

import com.google.gson.annotations.SerializedName;

/**
 * Descripcion larga de {@linkplain Item}
 */
public class ItemDescription {
    @SerializedName("text")
    private String text;
    @SerializedName("plain_text")
    private String plainText;

    public String getPlainText() {
        return plainText;
    }


}
