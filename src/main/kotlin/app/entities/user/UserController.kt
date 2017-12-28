package app.entities.user

import app.graphql.AuthContext
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class UserController @Autowired constructor(
        private val userRepository : UserRepository
) {

    fun users(env : DataFetchingEnvironment) : List<User> {
        return userRepository.findAll()
    }

    fun user(id : String, env : DataFetchingEnvironment) : User {
        return userRepository.findById(id)
    }
}