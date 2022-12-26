package org.example.highload.gatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutingConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(route -> route.path("/admin/**")
                .uri("lb://facade"))
                .route(route -> route.path("/customer/**")
                        .uri("lb://facade"))
                .route(route->route.path("/userorder/**")
                .uri("lb://facade"))
                .route(route->route.path("/restaurants/**")
                        .uri("lb://facade"))
                .route(route->route.path("/categories/**")
                        .uri("lb://facade"))
                .route(route->route.path("/recipes/**")
                        .uri("lb://facade"))
                .route(route->route.path("/dishes/**")
                        .uri("lb://facade"))
                .build();
    }
}
