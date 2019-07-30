package com.yogo.analytics.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class UtilityComponent {
    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }

@Bean
    public ExecutorService getExecutor(){
        return Executors.newWorkStealingPool();
}

@Bean
    public CategoryBuffer getCatBuffer(){
        return new CategoryBuffer();
}

@Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
}


}
