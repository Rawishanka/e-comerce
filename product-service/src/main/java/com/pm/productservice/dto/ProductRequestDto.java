package com.pm.productservice.dto;


import java.math.BigDecimal;


public record ProductRequestDto(String name, String description, BigDecimal price) {

}
