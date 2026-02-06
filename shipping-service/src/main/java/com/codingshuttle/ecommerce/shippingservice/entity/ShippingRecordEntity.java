package com.codingshuttle.ecommerce.shippingservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShippingRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingId;

    private Long orderId;

    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;

}
