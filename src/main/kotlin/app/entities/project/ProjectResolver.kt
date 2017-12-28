package app.entities.project

import app.entities.user.User
import app.entities.user.UserController
import app.entities.user.UserRepository
import com.coxautodev.graphql.tools.GraphQLResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProjectResolver @Autowired constructor(
        private val projectController : ProjectController,
        private val userController : UserController
) : GraphQLResolver<Project> {

    fun admin(project : Project, env : DataFetchingEnvironment) =
            userController.user(project.adminId, env)
}