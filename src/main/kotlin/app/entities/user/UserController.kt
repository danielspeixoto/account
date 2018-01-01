package app.entities.user

import app.auhentication.SocialOAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserController @Autowired constructor(
        private val userRepository : UserRepository
) {

    @Autowired
    lateinit private var socialAuth: SocialOAuth

    fun users(): List<User> {
        return userRepository.findAll()
    }

    fun user(id: String): User {
        return userRepository.findById(id)
    }

    fun signInFromGoogle(token: String): User? {
        val user = socialAuth.googleSignIn(token)
        if (user != null) {
            userRepository.save(user)
        }
        return user
    }
}