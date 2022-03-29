package com.learn.scarycoders.DDD.Domain.driven.design.learn.events;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private final String orderId;
    private final String productId;
}
