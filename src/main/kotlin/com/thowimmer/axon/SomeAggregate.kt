package com.thowimmer.axon

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class SomeAggregate {

    @AggregateIdentifier
    private lateinit var id : UUID

    @CommandHandler
    constructor(command: SomeCommand){
        AggregateLifecycle.apply(SomeEvent(command.id, command.count))
    }

    @EventSourcingHandler
    fun onSomeEvent(event: SomeEvent){
        id = event.id
    }
}