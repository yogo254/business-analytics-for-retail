package com.yogo.analytics.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductTranslation {

    private String id;

    private String catergoryName;

    private String englishName;

    public ProductTranslation() {
    }

    public ProductTranslation(String catergoryName, String englishName) {
        this.catergoryName = catergoryName;
        this.englishName = englishName;
    }

    public String getCatergoryName() {
        return catergoryName;
    }

    public void setCatergoryName(String catergoryName) {
        this.catergoryName = catergoryName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
}
