package app.comunication

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MessageSender {

    @Autowired
    lateinit private var template: RabbitTemplate

    fun sendMessage(msg: String, routingKey: String = MessagingConfig.QUEUE_NAME) {
        template.convertAndSend(routingKey, msg)
    }

}