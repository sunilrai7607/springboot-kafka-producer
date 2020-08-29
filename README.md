## Srpingboot Cloud Stream with Kafka broker
`kafka broker running on
localhost:9092
`
`
application.yml

spring:
  cloud:
    stream:
      default-binder: kafka
      kafka:
        binder:
          configuration:
            key.serializer: org.apache.kafka.common.serialization.StringSerializer
            value.serializer: org.apache.kafka.common.serialization.ByteArraySerializer
            key.deserializer: org.apache.kafka.common.serialization.StringDeserializer
            value.deserializer: org.apache.kafka.common.serialization.ByteArrayDeserializer
          broker:
            - localhost:9092
      bindings:
        input:
          binder: kafka
          destination: test
          content-type: text/plain
          group: input-group-1
 `
 
 ## Follow step to produce message
``
1. checkout https://github.com/sunilrai7607/kafka-docker-installation
2. run docker-compose up
3. MessageProducerApplication run the spring boot application
4. Consume the endpoint by postman http://localhost:8080/api/v1/messages/sendMessage
with payload
{
    "contents": "Hello Sunil Rai"
}
``