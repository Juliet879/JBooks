
package com.moringaschool.jbooks;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VolumeInfo {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("industryIdentifiers")
    @Expose
    private List<IndustryIdentifier> industryIdentifiers = null;
    @SerializedName("readingModes")
    @Expose
    private ReadingModes readingModes;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("maturityRating")
    @Expose
    private String maturityRating;
    @SerializedName("allowAnonLogging")
    @Expose
    private Boolean allowAnonLogging;
    @SerializedName("contentVersion")
    @Expose
    private String contentVersion;
    @SerializedName("panelizationSummary")
    @Expose
    private PanelizationSummary panelizationSummary;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinks imageLinks;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("previewLink")
    @Expose
    private String previewLink;
    @SerializedName("infoLink")
    @Expose
    private String infoLink;
    @SerializedName("canonicalVolumeLink")
    @Expose
    private String canonicalVolumeLink;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("categories")
    @Expose
    private List<String> categories = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public VolumeInfo() {
    }

    /**
     * 
     * @param industryIdentifiers
     * @param pageCount
     * @param printType
     * @param readingModes
     * @param previewLink
     * @param canonicalVolumeLink
     * @param language
     * @param title
     * @param imageLinks
     * @param panelizationSummary
     * @param subtitle
     * @param publishedDate
     * @param categories
     * @param maturityRating
     * @param allowAnonLogging
     * @param contentVersion
     * @param infoLink
     * @param authors
     */
    public VolumeInfo(String title, String publishedDate, List<IndustryIdentifier> industryIdentifiers, ReadingModes readingModes, Integer pageCount, String printType, String maturityRating, Boolean allowAnonLogging, String contentVersion, PanelizationSummary panelizationSummary, ImageLinks imageLinks, String language, String previewLink, String infoLink, String canonicalVolumeLink, String subtitle, List<String> authors, List<String> categories) {
        super();
        this.title = title;
        this.publishedDate = publishedDate;
        this.industryIdentifiers = industryIdentifiers;
        this.readingModes = readingModes;
        this.pageCount = pageCount;
        this.printType = printType;
        this.maturityRating = maturityRating;
        this.allowAnonLogging = allowAnonLogging;
        this.contentVersion = contentVersion;
        this.panelizationSummary = panelizationSummary;
        this.imageLinks = imageLinks;
        this.language = language;
        this.previewLink = previewLink;
        this.infoLink = infoLink;
        this.canonicalVolumeLink = canonicalVolumeLink;
        this.subtitle = subtitle;
        this.authors = authors;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public ReadingModes getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public PanelizationSummary getPanelizationSummary() {
        return panelizationSummary;
    }

    public void setPanelizationSummary(PanelizationSummary panelizationSummary) {
        this.panelizationSummary = panelizationSummary;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

}
