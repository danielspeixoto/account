package app.comunication

import app.Application
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class MessagingConfig {

    companion object {
        const val QUEUE_NAME = Application.NAME
        const val EXCHANGE = "$QUEUE_NAME-exchange"

        @Value("\${BROKER_USERNAME}")
        lateinit var USERNAME: String
        @Value("\${BROKER_PASSWORD}")
        lateinit var PASSWORD: String
    }

    @Bean
    fun queue(): Queue {
        return Queue(QUEUE_NAME, false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(EXCHANGE)
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME)
    }

    @Bean
    fun container(connectionFactory: ConnectionFactory,
                  listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(QUEUE_NAME)
        container.messageListener = listenerAdapter
        return container
    }

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory("rabbitmq")
        connectionFactory.username = USERNAME
        connectionFactory.setPassword(PASSWORD)
        return connectionFactory
    }

    @Bean
    fun listenerAdapter(receiver: MessageReceiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }
}