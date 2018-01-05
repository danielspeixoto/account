package app.comunication

import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch


@Component
class MessageReceiver {

    private val latch = CountDownLatch(1)

    fun receiveMessage(message: String) {
        println("Received <$message>")
        latch.countDown()
    }

}