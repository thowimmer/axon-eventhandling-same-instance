package com.thowimmer.axon

import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.messaging.annotation.MetaDataValue
import org.springframework.stereotype.Component

@Component
class SomeEventHandler {

    @EventSourcingHandler
    fun handleSomeEvent(event: SomeEvent, @MetaDataValue("pid") pid : String?){
        logger().info("Process $pid published event ${event.count}")
    }
}