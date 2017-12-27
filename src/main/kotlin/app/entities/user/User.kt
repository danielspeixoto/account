package app.entities.user

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed

data class User(
        @Indexed val email: String,
        val projectsId: List<String> = listOf()
) {
    @Id val id: String? = null
}