
package com.moringaschool.jbooks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pdf {

    @SerializedName("isAvailable")
    @Expose
    private Boolean isAvailable;
    @SerializedName("downloadLink")
    @Expose
    private String downloadLink;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Pdf() {
    }

    /**
     * 
     * @param isAvailable
     * @param downloadLink
     */
    public Pdf(Boolean isAvailable, String downloadLink) {
        super();
        this.isAvailable = isAvailable;
        this.downloadLink = downloadLink;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

}
