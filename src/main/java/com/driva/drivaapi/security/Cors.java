package com.driva.drivaapi.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Cors {


    // @Bean
    // public CorsFilter corsFilter() {
    //     UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration corsConfiguration = new CorsConfiguration();
    //     corsConfiguration.setAllowCredentials(true);
    //     corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
    //     corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
    //             "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
    //             "Access-Control-Request-Method", "Access-Control-Request-Headers"));
    //     corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
    //             "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
    //     corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    //     urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    //     return new CorsFilter(urlBasedCorsConfigurationSource);
    // }
}
