package app.entities.project

import app.entities.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProjectController @Autowired constructor(
        private val repository: ProjectRepository
) {

    fun projects(idList: List<String>, user: User): List<Project> {
        // TODO Check if user has permissions to see those projects
        return repository.findAll(idList) as List<Project>
    }
}