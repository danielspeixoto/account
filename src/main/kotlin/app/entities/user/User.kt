package app.entities.user

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User @PersistenceConstructor constructor(
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