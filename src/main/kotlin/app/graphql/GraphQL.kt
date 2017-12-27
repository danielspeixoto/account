package app.graphql

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class GraphQL {

    @Bean
    open fun query() = Query()

}


