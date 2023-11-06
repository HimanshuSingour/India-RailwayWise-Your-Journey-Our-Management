package com.loco.v1.wise.locomotive.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/train/v7/server/add-train")
                .permitAll()
                .requestMatchers("/train/v7/server/add-bogies")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();

        return httpSecurity.build();
    }
}
