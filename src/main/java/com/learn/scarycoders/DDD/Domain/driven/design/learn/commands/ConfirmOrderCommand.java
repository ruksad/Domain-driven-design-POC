package com.learn.scarycoders.DDD.Domain.driven.design.learn.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ConfirmOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
}
