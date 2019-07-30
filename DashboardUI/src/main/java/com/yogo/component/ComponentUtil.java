package com.yogo.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentUtil {
    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }

}
