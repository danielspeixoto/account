package app.entities.project

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProjectController @Autowired constructor(
        private val repository: ProjectRepository
) {

    fun projects(idList: List<String>, userId: String?): List<Project> {
        // A user can only sees public projects or private projects
        // he is part of
        return repository.findAll(idList).filter {
            (
                    it.isPublic ||
                            (userId != null && it.membersIds.contains(userId))
                    )
        }
    }
}