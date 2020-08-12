package com.thowimmer.axon

import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.EventMessage
import org.axonframework.messaging.InterceptorChain
import org.axonframework.messaging.Message
import org.axonframework.messaging.MessageHandlerInterceptor
import org.axonframework.messaging.correlation.CorrelationDataProvider
import org.axonframework.messaging.unitofwork.UnitOfWork
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.system.ApplicationPid
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

const val METADATA_KEY_PROCESS_ID = "pid"
const val PROCESSING_GROUP_PREFIX = "processing-group-pid"

@Configuration
class AxonConfiguration {

    @Bean
    fun processIdCorrelationDataProvider() = ProcessIdCorrelationDataProvider()

    @Autowired
    fun configureProcessIdEventHandlerInterceptor(eventProcessingConfigurer: EventProcessingConfigurer) {
        val processingGroup = "$PROCESSING_GROUP_PREFIX-${ApplicationPid()}"
        eventProcessingConfigurer.byDefaultAssignTo(processingGroup)
        eventProcessingConfigurer.registerHandlerInterceptor(processingGroup) { ProcessIdEventHandlerInterceptor() }
    }
}

class ProcessIdCorrelationDataProvider() : CorrelationDataProvider {
    override fun correlationDataFor(message: Message<*>?): MutableMap<String, *> {
        return mutableMapOf(METADATA_KEY_PROCESS_ID to ApplicationPid().toString())
    }
}

class ProcessIdEventHandlerInterceptor : MessageHandlerInterceptor<EventMessage<*>> {
    override fun handle(unitOfWork: UnitOfWork<out EventMessage<*>>?, interceptorChain: InterceptorChain?) {
        val currentPid = ApplicationPid().toString()
        val originPid = unitOfWork?.message?.metaData?.get(METADATA_KEY_PROCESS_ID)
        if(currentPid == originPid){
            interceptorChain?.proceed()
        }
    }
}
