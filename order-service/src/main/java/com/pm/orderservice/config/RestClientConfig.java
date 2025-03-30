package com.pm.orderservice.config;

import com.pm.orderservice.client.InventoryClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@ConfigurationProperties(prefix ="inventory.feign")
public class RestClientConfig {
    private String url;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Bean
    public InventoryClient inventoryClient() {
        RestClient restClient = RestClient.builder().baseUrl(this.url).build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var httpServiceProxyFactor = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactor.createClient(InventoryClient.class);

    }
}
