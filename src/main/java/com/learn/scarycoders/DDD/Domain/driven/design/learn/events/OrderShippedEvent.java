package com.learn.scarycoders.DDD.Domain.driven.design.learn.events;

import lombok.Data;

@Data
public class OrderShippedEvent {
    private final String orderId;
}
