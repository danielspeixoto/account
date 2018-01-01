package app.entities.project

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Project @PersistenceConstructor constructor(
        @Indexed val name: String,
        val adminId: String,
        val isPublic: Boolean = true,
        val membersIds: List<String> = listOf(adminId),
        @Id
        val id: String? = null
)