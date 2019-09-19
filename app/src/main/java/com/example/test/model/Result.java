package com.example.test.model;
import androidx.databinding.BindingAdapter;

import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class Result {
    @SerializedName("id")
    private String id;
    @SerializedName("site_id")
    private String siteId;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private Double price;
    @SerializedName("currency_id")
    private String currencyId;
    @SerializedName("available_quantity")
    private Integer availableQuantity;
    @SerializedName("sold_quantity")
    private Integer soldQuantity;
    @SerializedName("buying_mode")
    private String buyingMode;
    @SerializedName("listing_type_id")
    private String listingTypeId;
    @SerializedName("stop_time")
    private String stopTime;
    @SerializedName("condition")
    private String condition;
    @SerializedName("permalink")
    private String permalink;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("accepts_mercadopago")
    private Boolean acceptsMercadopago;
    @SerializedName("original_price")
    private Double originalPrice;
    @SerializedName("category_id")
    private String categoryId;
    @SerializedName("official_store_id")
    private Integer officialStoreId;
    @SerializedName("catalog_product_id")
    private Object catalogProductId;
    @SerializedName("tags")
    private List<String> tags = null;

    public String getId() {
        return id;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public String getBuyingMode() {
        return buyingMode;
    }

    public String getListingTypeId() {
        return listingTypeId;
    }

    public String getStopTime() {
        return stopTime;
    }

    public String getCondition() {
        return condition;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Boolean getAcceptsMercadopago() {
        return acceptsMercadopago;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Integer getOfficialStoreId() {
        return officialStoreId;
    }

    public Object getCatalogProductId() {
        return catalogProductId;
    }

    public List<String> getTags() {
        return tags;
    }
}
