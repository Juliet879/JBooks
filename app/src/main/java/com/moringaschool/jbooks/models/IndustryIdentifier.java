
package com.moringaschool.jbooks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class IndustryIdentifier {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("identifier")
    @Expose
    private String identifier;

    /**
     * No args constructor for use in serialization
     * 
     */
    public IndustryIdentifier() {
    }

    /**
     * 
     * @param identifier
     * @param type
     */
    public IndustryIdentifier(String type, String identifier) {
        super();
        this.type = type;
        this.identifier = identifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
