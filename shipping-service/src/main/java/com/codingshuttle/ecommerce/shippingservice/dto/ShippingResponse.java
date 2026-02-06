package com.codingshuttle.ecommerce.shippingservice.dto;

import lombok.Data;

@Data
public class ShippingResponse {

    private Long shippingId;
    private Long orderId;
    private String shippingStatus;
}
