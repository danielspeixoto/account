package app.entities.project

import app.entities.user.UserController
import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProjectResolver @Autowired constructor(
        private val projectController : ProjectController,
        private val userController : UserController
) : GraphQLResolver<Project> {

    fun admin(project: Project) =
            userController.user(project.adminId)
}