package com.learn.scarycoders.DDD.Domain.driven.design.learn.queryModel;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Order {
    private final String orderId;
    private final String productId;
    private OrderStatus orderStatus;

    public void setOrderConfirmed() {
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    public void setOrderShipped(){
        this.orderStatus=OrderStatus.SHIPPED;
    }

}
