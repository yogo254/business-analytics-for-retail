package com.yogo.service.analytics.predictive;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SalesForecast {
    private static  RestTemplate restTemplate =new RestTemplate();



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

    public static List<FeaturesCase> dayMonthWeekPrediction(List<FeaturesCase> featuresCases){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        List<FeaturesCase>featuresCases1=new ArrayList<>();
        for (FeaturesCase f:featuresCases
        ) {
            HttpEntity<FeaturesCase> httpEntity=new HttpEntity<>(f,headers);
            ResponseEntity<FeaturesCase>responseEntity= restTemplate.postForEntity("http://localhost:8061/api/sales/daymonth/week",httpEntity,FeaturesCase.class);
            featuresCases1.add(responseEntity.getBody());

        }

        return featuresCases1;


    }

    public static List<FeaturesCase> monthMonthWeekPrediction(List<FeaturesCase> featuresCases){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        List<FeaturesCase>featuresCases1=new ArrayList<>();
        for (FeaturesCase f:featuresCases
        ) {
            HttpEntity<FeaturesCase> httpEntity=new HttpEntity<>(f,headers);
            ResponseEntity<FeaturesCase>responseEntity= restTemplate.postForEntity("http://localhost:8061/api/sales/month/monthweek",httpEntity,FeaturesCase.class);
            featuresCases1.add(responseEntity.getBody());

        }

        return featuresCases1;


    }

}
