package com.yogo.service.analytics.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.domain.products.CatergoryValue;
import com.yogo.domain.products.ProductCounts;
import com.yogo.domain.products.ProductStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductAnalytics {

    @Autowired
    private ObjectMapper mapper;

    public ProductAnalytics() {
        monitor();
    }

    private void monitor() {


    }

    @KafkaListener(topics = "products")
public void receive(String message){

ProductStatistics statistics=deserialize(message);
List<CatergoryValue> catergoryValue=statistics.getTopCartegories();
ProductUI.getTopCategories().offer(catergoryValue);

ProductCounts resentProducts=statistics.getProductCounts();
ProductUI.getProductCounts().offer(resentProducts);
ProductUI.getDoubleStats().offer(statistics.getStatistics());





}
private ProductStatistics deserialize(String json){
    ProductStatistics statistics=new ProductStatistics();
    try {
        statistics=mapper.readValue(json,ProductStatistics.class);
    } catch (IOException e) {
        e.printStackTrace();
    }

    return statistics;
}


}
