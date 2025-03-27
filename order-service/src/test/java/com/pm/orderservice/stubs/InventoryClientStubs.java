package com.pm.orderservice.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStubs {
    public static void inventoryGetCall(String skuCode, Integer quantity) {
        stubFor(get(urlEqualTo(String.format("/api/inventory/check?skuCode="+ skuCode + "&quantity="+ quantity)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));

    }
}
