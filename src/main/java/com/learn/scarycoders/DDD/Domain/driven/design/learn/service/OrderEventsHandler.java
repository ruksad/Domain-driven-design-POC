package com.learn.scarycoders.DDD.Domain.driven.design.learn.service;

import com.learn.scarycoders.DDD.Domain.driven.design.learn.events.OrderCreatedEvent;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.queryModel.FindAllOrderedProductsQuery;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.queryModel.Order;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderEventsHandler {

    private final Map<String, Order> orders = new HashMap<>();

    @EventHandler
    public void on(OrderCreatedEvent orderCreateEvent) {
        String orderId = orderCreateEvent.getOrderId();
        orders.put(orderId, new Order(orderId, orderCreateEvent.getProductId()));
    }

    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery allOrderedProducts) {
        return new ArrayList<>(orders.values());
    }
}
