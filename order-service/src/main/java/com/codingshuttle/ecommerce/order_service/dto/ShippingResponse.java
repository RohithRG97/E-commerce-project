package com.codingshuttle.ecommerce.order_service.dto;

import lombok.Data;

@Data
public class ShippingResponse {

    private Long shippingId;
    private Long orderId;
    private String shippingStatus;
}
