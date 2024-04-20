package com.example.gateway.config;

import com.example.gateway.filter.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConfigRouter {
    private final JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("Users-services", r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://Users-Services"))

                .route("Paiement-services", r -> r.path("/paiement/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://Paiement-Services"))

                .route("Authentification-Security", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://Authentification-Security"))

                .route("Paniers-Services", r -> r.path("/panier/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://Paniers-Services"))

                .route("Products-Services", r -> r.path("/product/**")
                        .uri("lb://Products-Services"))

                .build();
    }


}
