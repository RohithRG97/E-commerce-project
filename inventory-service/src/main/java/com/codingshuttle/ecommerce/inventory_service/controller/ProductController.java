package com.codingshuttle.ecommerce.inventory_service.controller;

import com.codingshuttle.ecommerce.inventory_service.clients.OrdersFeignClient;
import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDTO;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDTO;
import com.codingshuttle.ecommerce.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchOrders")
    public String getOrders() {
//        ServiceInstance orderService = discoveryClient.getInstances("order-service").getFirst();
//
//        URI uri = URI.create(
//                orderService.getUri() + "/orders/core/helloOrders"
//        );
//
//        return restClient.get()
//                .uri(uri)
//                .retrieve()
//                .body(String.class);

        return ordersFeignClient.helloOrders();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {

        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    @PutMapping("/reduce-stocks")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequestDTO orderRequestDTO) {
        Double totalPrice = productService.reduceStocks(orderRequestDTO);
        return ResponseEntity.ok(totalPrice);
    }

    @PutMapping("/add-stocks")
    public ResponseEntity<Double> addStocks(@RequestBody OrderRequestDTO orderRequestDTO) {
        Double totalPrice = productService.addStocks(orderRequestDTO);
        return ResponseEntity.ok(totalPrice);
    }
}
