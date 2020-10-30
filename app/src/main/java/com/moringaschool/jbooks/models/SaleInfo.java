
package com.moringaschool.jbooks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class SaleInfo {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("saleability")
    @Expose
    private String saleability;
    @SerializedName("isEbook")
    @Expose
    private Boolean isEbook;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SaleInfo() {
    }

    /**
     * 
     * @param country
     * @param isEbook
     * @param saleability
     */
    public SaleInfo(String country, String saleability, Boolean isEbook) {
        super();
        this.country = country;
        this.saleability = saleability;
        this.isEbook = isEbook;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public Boolean getIsEbook() {
        return isEbook;
    }

    public void setIsEbook(Boolean isEbook) {
        this.isEbook = isEbook;
    }

}
