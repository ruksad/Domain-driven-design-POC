package com.learn.scarycoders.DDD.Domain.driven.design.learn.controller;

import com.learn.scarycoders.DDD.Domain.driven.design.learn.commands.ConfirmOrderCommand;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.commands.CreateOrderCommand;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.commands.ShipOrderCommand;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.queryModel.FindAllOrderedProductsQuery;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.queryModel.Order;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class OrderRestEndPoint {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    OrderRestEndPoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/ship-order")
    public CompletableFuture<Void> shipOrder() {
        String s = UUID.randomUUID().toString();
        return commandGateway.send(new CreateOrderCommand(s, "Comfy couch"))
                .thenCompose(result -> commandGateway.send(new ConfirmOrderCommand(s)))
                .thenCompose(result -> commandGateway.send(new ShipOrderCommand(s)));
    }

    @GetMapping("/all-orders")
    public CompletableFuture<List<Order>> findAllOrders() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(Order.class));
    }
}
