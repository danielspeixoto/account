package app

import app.comunication.MessageSender
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Autowired
    lateinit var sender: MessageSender

    companion object {

        const val NAME = "account"

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }

    private val logger = LoggerFactory.getLogger(Application::class.java.name)

    @Bean
    fun init() = CommandLineRunner {
        // Runs at start of application
        sender.sendMessage("testing daniel")
        logger.info("Application started: Running init")
    }

}