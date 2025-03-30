package com.pm.apigateway.route;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
@Tag(name = "API GateWay", description = "This is API end point for all services")
public class Routes {
    @Bean
    @Operation(summary = "handle product calls")
    public RouterFunction<ServerResponse> productRoutes(HandlerFunction handlerFunction) {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("api/product"),
                        HandlerFunctions.http("http://localhost:4000"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("product_service_circuit_breaker", URI.create("forward:fallback")))
                .build();

    }
    @Bean
    @Operation(summary = "handle inventory calls")
    public RouterFunction<ServerResponse> inventoryService(HandlerFunction handlerFunction) {
        return GatewayRouterFunctions.route("inventory_service")
                .route(RequestPredicates.path("api/inventory"),
                        HandlerFunctions.http("http://localhost:4002"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventory_service_circuit_breaker", URI.create("forward:fallback")))
                .build();

    }
    @Bean
    @Operation(summary = "handle order calls")
    public RouterFunction<ServerResponse> orderService(HandlerFunction handlerFunction) {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("api/order"),
                        HandlerFunctions.http("http://localhost:4001"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("order_service_circuit_breaker", URI.create("forward:fallback")))
                .build();

    }
    @Bean
    public RouterFunction<ServerResponse> fallbackService() {
        return route("fallbackRoute").GET(request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("service unavailable due to service fall ")).build();
    }

}
