package com.healthcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow your React frontend origin
        config.addAllowedOrigin("http://localhost:5173");  // Vite default port
        config.addAllowedOrigin("http://localhost:3000");  // If using Create React App

        // For production (when deployed), replace with your actual frontend domain
        // config.addAllowedOrigin("https://your-frontend-domain.com");

        // Or allow all origins during development only (remove in production!)
        // config.setAllowedOrigins(java.util.List.of("*"));

        config.addAllowedMethod("*"); // Allow GET, POST, PUT, DELETE, OPTIONS, etc.
        config.addAllowedHeader("*"); // Allow all headers (Content-Type, Authorization, etc.)
        config.setAllowCredentials(true); // Important if you plan to use cookies or auth tokens later

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all endpoints

        return new CorsFilter(source);
    }
}
