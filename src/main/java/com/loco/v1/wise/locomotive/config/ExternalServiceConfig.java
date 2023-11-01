package com.loco.v1.wise.locomotive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalServiceConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
