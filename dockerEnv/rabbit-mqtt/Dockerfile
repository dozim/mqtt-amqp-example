FROM rabbitmq:3-management

RUN rabbitmq-plugins enable --offline rabbitmq_management
RUN rabbitmq-plugins enable --offline rabbitmq_mqtt
RUN rabbitmq-plugins enable --offline rabbitmq_web_mqtt

# MANAGEMENT
EXPOSE 15672
# MQTT WITH WEBSOCKET
EXPOSE 15675
# MQTT
EXPOSE 1883
# MQTT TLS
EXPOSE 8883
# AMQP
EXPOSE 5672
