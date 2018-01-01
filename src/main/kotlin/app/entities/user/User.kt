package app.entities.user

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed

data class User(
        @Indexed val email: String,
        val name: String = "",
        val projectsId: List<String> = listOf(),
        @Id val id: String? = null
) {

    constructor(googlePayload: GoogleIdToken.Payload) : this(
            email = googlePayload.email,
            name = googlePayload["name"] as String
    )
}