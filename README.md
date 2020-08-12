# Axon Spring Boot Demo - Handle only events published by same instance
This Spring Boot demo project shows how to configure the Axon Framwork, so that Events are only handled by the instances which publish them.

See [Stack Overflow discussion](https://stackoverflow.com/questions/63313532) with [Steven van Beelen](https://github.com/smcvb) for further details.

# Basic Axon Setup
* [Axon Spring Boot Starter](https://docs.axoniq.io/reference-guide/axon-framework/spring-boot-integration):
Spring Boot Autoconfiguration for Axon.

* [JpaEventStorageEngine](https://docs.axoniq.io/reference-guide/axon-framework/events/event-bus-and-event-store#jpaeventstorageengine):
A SQL Server based JpaEventStorageEngine as [EventStore](https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors#token-store) & [TokenStore](https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors#token-store).

* [TrackingeEventProcessor](https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors):
A TrackingEventProcessor for a single EventSource is configured (default of Axon Spring Boot Starter).

* [CorrelationDataProvider](https://docs.axoniq.io/reference-guide/axon-framework/events/event-processors):
A CorrelationDataProvider is used to add Metadata to every published Event.

* [MessageHandlerInterceptor](https://docs.axoniq.io/reference-guide/axon-framework/messaging-concepts/message-intercepting#event-handler-interceptors):
A MessageHandlerInterceptor is used filter Events which should be processed by the same application instance.

# How to run
## 1) Start Docker Compose based SQL Server instance
Use the [sql-server-test-db](/.run/sql-server-test-db.run.xml) run config if you're using IntelliJ IDEA or start the [docker-compose.yml](/src/docker/sql-server-test-db/docker-compose.yml) otherwise.

## 2) Start instance of Spring Boot application
Use the [new-app-instance](/.run/new-app-instance.run.xml) run config if you're using IntelliJ IDEA or start the [Application.kt](src/main/kotlin/com/thowimmer/axon/Application.kt) otherwise.
