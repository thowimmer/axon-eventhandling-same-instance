package com.thowimmer.axon

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class SomeCommand(
        @TargetAggregateIdentifier val id: UUID,
        val count : Long)

data class SomeEvent(
        val id: UUID,
        val count : Long)