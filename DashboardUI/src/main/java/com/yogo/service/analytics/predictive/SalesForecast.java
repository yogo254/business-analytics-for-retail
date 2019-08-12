package com.yogo.service.analytics.predictive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SalesForecast {
    private static  RestTemplate restTemplate =new RestTemplate();
    private static ObjectMapper mapper=new ObjectMapper();


   public static List<FeaturesCase> dayHourPrediction(List<FeaturesCase> featuresCases){
       HttpHeaders headers=new HttpHeaders();
       headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
       List<FeaturesCase>featuresCases1=new ArrayList<>();
       for (FeaturesCase f:featuresCases
            ) {
           HttpEntity<FeaturesCase> httpEntity=new HttpEntity<>(f,headers);
           ResponseEntity<FeaturesCase>responseEntity= restTemplate.postForEntity("http://localhost:8061/api/sales/dayhour",httpEntity,FeaturesCase.class);
           featuresCases1.add(responseEntity.getBody());

       }

        return featuresCases1;


    }
    private static String serialize(Object o){
        String json=null;
        try {
            json=mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    private static List<FeaturesCase>desialize(String json){
        List<FeaturesCase> caseList=new ArrayList<>();
        try {
            caseList=mapper.readValue(json,new TypeReference<List<FeaturesCase>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return caseList;
    }
}
