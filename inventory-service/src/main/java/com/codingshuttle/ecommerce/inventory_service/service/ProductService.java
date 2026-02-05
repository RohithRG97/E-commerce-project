package com.codingshuttle.ecommerce.inventory_service.service;

import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestDTO;
import com.codingshuttle.ecommerce.inventory_service.dto.OrderRequestItemDTO;
import com.codingshuttle.ecommerce.inventory_service.dto.ProductDTO;
import com.codingshuttle.ecommerce.inventory_service.entity.Product;
import com.codingshuttle.ecommerce.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductDTO getProductById(Long id) {
        log.info("Fetching product with id: {}", id);

        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> getAllProducts() {
        log.info("Fetching all inventories");
        List<Product> inventories = productRepository.findAll();

        return inventories.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    @Transactional
    public Double reduceStocks(OrderRequestDTO orderRequestDTO) {
        log.info("Reducing the stocks");
        Double totalPrice = 0.0;
        for(OrderRequestItemDTO orderRequestItemDTO: orderRequestDTO.getItems()) {
            Long productId = orderRequestItemDTO.getProductId();
            Integer quantity = orderRequestItemDTO.getQuantity();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if(product.getStock() < quantity) {
                throw new RuntimeException("Not enough stock");
            }

            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
            totalPrice += quantity * product.getPrice();
        }
        return totalPrice;
    }

    public Double addStocks(OrderRequestDTO orderRequestDTO) {
        log.info("Reducing the stocks");
        Double totalPrice = 0.0;
        for(OrderRequestItemDTO orderRequestItemDTO: orderRequestDTO.getItems()) {
            Long productId = orderRequestItemDTO.getProductId();
            Integer quantity = orderRequestItemDTO.getQuantity();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setStock(product.getStock() + quantity);
            productRepository.save(product);
            totalPrice += quantity * product.getPrice();
        }
        return totalPrice;
    }
}
