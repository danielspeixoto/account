package app.entities.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class UserController @Autowired constructor(
        private val userRepository : UserRepository
) {

    fun users(): List<User> {
        return userRepository.findAll()
    }

    fun user(id: String): User {
        return userRepository.findById(id)
    }
}