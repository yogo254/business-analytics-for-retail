package com.yogo.analytics.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.analytics.component.CategoryBuffer;
import com.yogo.analytics.domain.ProductTranslation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Service
public class CategoryFinder {

    @Autowired
    private RestTemplate template;
    @Autowired
    private CategoryBuffer buffer;

    public String getCategory(String productId) {
        String category = buffer.getBuffer().getOrDefault(productId, "");

        if (category.equals("")) {
            ProductTranslation translation;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            HttpEntity<ProductTranslation> httpEntity = new HttpEntity<>(headers);
            translation = template.exchange("http://127.0.0.1:8021/api/products/tran/id/" + productId, HttpMethod.GET, httpEntity, ProductTranslation.class)
                    .getBody();
category=translation.getEnglishName();
buffer.getBuffer().put(productId,category);

        }
        return category;

    }

}