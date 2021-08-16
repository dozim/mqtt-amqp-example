FROM rabbitmq:3-management

RUN rabbitmq-plugins enable --offline rabbitmq_management

# MANAGEMENT
EXPOSE 15672
# AMQP
EXPOSE 5672
# METRICS
EXPOSE 15692