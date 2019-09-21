package com.example.test.model;

import com.google.gson.annotations.SerializedName;

/**
 * Descripcion larga de {@linkplain Item}
 */
public class ItemDescription {
    @SerializedName("text")
    private String text;
    @SerializedName("plain_text")
    private String plainText;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

}
