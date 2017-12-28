package app.graphql

import app.entities.user.UserController
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Query(
        @Autowired
        private val userController : UserController
) : GraphQLQueryResolver {

    fun users(env : DataFetchingEnvironment)
            = userController.users(env)
}