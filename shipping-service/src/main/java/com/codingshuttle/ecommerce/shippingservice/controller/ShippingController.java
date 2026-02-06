package com.codingshuttle.ecommerce.shippingservice.controller;

import com.codingshuttle.ecommerce.shippingservice.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.shippingservice.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("/createShippingRecord")
    public ResponseEntity<String> createShippingRecord(@RequestBody OrderRequestDto orderRequestDto) {
        String shippingResponse = shippingService.createShippingRecord(orderRequestDto);
        return ResponseEntity.ok(shippingResponse);
    }
}
