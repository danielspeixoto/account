package app.entities.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

data class User(
        val email : String
) {
    @Id val id: String? = null
}