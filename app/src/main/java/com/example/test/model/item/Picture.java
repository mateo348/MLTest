
package com.example.test.model.item;

import com.google.gson.annotations.SerializedName;

/**
 * Representa cada imagen de {@linkplain Item}
 */
public class Picture {

    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("secure_url")
    private String secureUrl;
    @SerializedName("size")
    private String size;
    @SerializedName("max_size")
    private String maxSize;
    @SerializedName("quality")
    private String quality;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

}
