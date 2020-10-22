
package com.moringaschool.jbooks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moringaschool.jbooks.models.Item;
import com.moringaschool.jbooks.models.VolumeInfo;

public class GoogleBookSearchResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("totaItemlItems")
    @Expose
    private Integer totalItems;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("volumeInfo")
    @Expose
    private List<VolumeInfo> volumeInfo = null;
    /**
     * No args constructor for use in serialization
     * 
     */
    public GoogleBookSearchResponse() {
    }

    /**
     * 
     * @param totalItems
     * @param kind
     * @param volumeInfo
     * @param items
     */
    public GoogleBookSearchResponse(String kind, Integer totalItems, List<Item> items, List<VolumeInfo> volumeInfo) {
        super();
        this.kind = kind;
        this.totalItems = totalItems;
        this.items = items;
        this.volumeInfo = volumeInfo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<VolumeInfo> getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(List<VolumeInfo> volumeInfo) {
        this.volumeInfo = volumeInfo;
    }


}
