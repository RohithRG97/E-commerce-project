package com.codingshuttle.ecommerce.order_service.clients;

import com.codingshuttle.ecommerce.order_service.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.order_service.dto.ShippingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "shipping-service", path="/shipping")
public interface ShippingOpenFeignClient {

    @PostMapping("/core/createShippingRecord")
    String createShippingRecord(@RequestBody OrderRequestDto orderRequestDto);
}
