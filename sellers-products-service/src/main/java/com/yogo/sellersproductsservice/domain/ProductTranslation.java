package com.yogo.sellersproductsservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("product_cat_translation")
public class ProductTranslation {
    @Id
    private String id;

    @Field("category_name")
    private String catergoryName;
    @Field("english_name")
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
