package com.example.antitouch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // ко всем эндпоинтам
                        .allowedOrigins("*") // разрешить любые источники
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // разрешённые методы
                        .allowedHeaders("*"); // разрешить любые заголовки
            }
        };
    }
}
