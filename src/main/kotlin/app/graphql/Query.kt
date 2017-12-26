package app.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.context.annotation.Bean

class Query : GraphQLQueryResolver {

    fun hello() = "world"
}