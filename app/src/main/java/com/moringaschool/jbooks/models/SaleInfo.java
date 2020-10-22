
package com.moringaschool.jbooks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("buyLink")
    @Expose
    private String buyLink;

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
     * @param buyLink
     */
    public SaleInfo(String country, String saleability, Boolean isEbook, String buyLink) {
        super();
        this.country = country;
        this.saleability = saleability;
        this.isEbook = isEbook;
        this.buyLink = buyLink;
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

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

}
