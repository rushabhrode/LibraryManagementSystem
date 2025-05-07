package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/v1/signup/**",
                    "/api/v1/login/**",
                    "/api/algotest",
                    "/api/v1/forgotpassword/**",
                    "/api/v1/filter/**",
                    "/api/v1/book/**",
                    "/api/v1/recentBooks/**",
                    "/api/v1/featuredBooks/**",
                    "/api/v1/popularBooks/**",
                    "/uploads/**"
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}