package app.entities.project

import app.entities.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProjectController @Autowired constructor(
        private val repository: ProjectRepository
) {

    fun projects(idList: List<String>, user: User): List<Project> {
        // A user can only sees public projects or private projects
        // he is part of
        return (repository.findAll(idList) as List<Project>).filter {
            it.isPublic || it.membersIds.contains(user.id)
        }
    }
}