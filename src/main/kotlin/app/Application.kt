package app

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

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
        logger.info("Application started: Running init")
    }

}