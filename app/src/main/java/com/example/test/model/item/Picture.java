
package com.example.test.model.item;

import com.google.gson.annotations.SerializedName;

/**
 * Representa cada imagen de {@linkplain Item}
 */
public class Picture {

    @SerializedName("id")
    private String id;
    @SerializedName("secure_url")
    private String secureUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecureUrl() {
        return secureUrl;
    }

}
