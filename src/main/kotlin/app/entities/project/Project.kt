package app.entities.project

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed

data class Project(
        @Indexed val name: String,
        val adminId: String,
        val isPublic: Boolean = true,
        val membersIds: List<String> = listOf(adminId),
        @Id
        val id: String? = null
)