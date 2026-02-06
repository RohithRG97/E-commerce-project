package com.codingshuttle.ecommerce.shippingservice.service;

import com.codingshuttle.ecommerce.shippingservice.dto.OrderRequestDto;
import com.codingshuttle.ecommerce.shippingservice.entity.ShippingRecordEntity;
import com.codingshuttle.ecommerce.shippingservice.entity.ShippingStatus;
import com.codingshuttle.ecommerce.shippingservice.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public String createShippingRecord(OrderRequestDto orderRequestDto) {
        ShippingRecordEntity shippingRecord = new ShippingRecordEntity();
        shippingRecord.setOrderId(orderRequestDto.getId());
        shippingRecord.setShippingStatus(ShippingStatus.PENDING);

        ShippingRecordEntity savedShippingRecord = shippingRepository.save(shippingRecord);
        return savedShippingRecord.getShippingStatus().toString();
    }
}
