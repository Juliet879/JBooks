
package com.moringaschool.jbooks.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moringaschool.jbooks.models.Item;

import org.parceler.Parcel;

@Parcel
public class GoogleBooksSearchResponse {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GoogleBooksSearchResponse() {
    }

    /**
     * 
     * @param totalItems
     * @param kind
     * @param items
     */
    public GoogleBooksSearchResponse(String kind, Integer totalItems, List<Item> items) {
        super();
        this.kind = kind;
        this.totalItems = totalItems;
        this.items = items;
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

}
