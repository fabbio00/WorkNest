package com.ams.worknest.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuration class for setting up CORS (Cross-Origin Resource Sharing) in the application.
 * This configuration allows the application to accept cross-origin requests from localhost.
 */
@Configuration
public class WebConfiguration {

    /**
     * Creates a CORS filter bean with specific configurations to handle CORS requests.
     * This method sets up the allowed origins, headers, and methods for CORS.
     *
     * @return the configured CorsFilter bean
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allows credentials to be included in the CORS requests.
        config.addAllowedOrigin("http://localhost:8000"); // Specifies the allowed origin for CORS requests.
        config.addAllowedHeader("*"); // Allows all headers in CORS requests.
        config.addAllowedMethod("*"); // Allows all HTTP methods in CORS requests.
        source.registerCorsConfiguration("/**", config); // Applies the CORS configuration to all paths.
        return new CorsFilter(source);
    }
}