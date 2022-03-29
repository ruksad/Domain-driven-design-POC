package com.learn.scarycoders.DDD.Domain.driven.design.learn.aggregates;

import com.learn.scarycoders.DDD.Domain.driven.design.learn.commands.ConfirmOrderCommand;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.commands.CreateOrderCommand;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.commands.ShipOrderCommand;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.events.OrderConfirmedEvent;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.events.OrderCreatedEvent;
import com.learn.scarycoders.DDD.Domain.driven.design.learn.events.OrderShippedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand orderCommand) {
        apply(new OrderCreatedEvent(orderCommand.getOrderId(), orderCommand.getProductId()));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand confirmOrderCommand) {
        if (orderConfirmed)
            return;
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand shipOrderCommand) {
        if (!orderConfirmed) {
            throw new RuntimeException("Unconfirmed order exception");
        }
        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreateEvent) {
        this.orderId = orderCreateEvent.getOrderId();
        this.orderConfirmed = false;
    }

    protected OrderAggregate() {
    }
}
