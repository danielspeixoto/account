package app.graphql

import app.entities.user.UserController
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class QueryResolver @Autowired constructor(
        private val userController : UserController
) : GraphQLQueryResolver {

    fun users()
            = userController.users()
}