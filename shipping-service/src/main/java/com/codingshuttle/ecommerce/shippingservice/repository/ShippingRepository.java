package com.codingshuttle.ecommerce.shippingservice.repository;

import com.codingshuttle.ecommerce.shippingservice.entity.ShippingRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingRecordEntity, Long> {
}
