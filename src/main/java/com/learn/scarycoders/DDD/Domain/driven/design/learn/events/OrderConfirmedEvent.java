package com.learn.scarycoders.DDD.Domain.driven.design.learn.events;

import lombok.Data;

@Data
public class OrderConfirmedEvent {
    private final String orderId;
}
