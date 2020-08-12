package com.thowimmer.axon

import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class SomeCommandPublisher(val commandGateway: CommandGateway) {

    var counter = 0L

    @Scheduled(fixedRate = 1000)
    fun publishCommand(){
        commandGateway.send<SomeCommand>(SomeCommand(UUID.randomUUID(), counter++))
    }
}