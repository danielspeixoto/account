package app.graphql

import app.entities.project.ProjectController
import app.entities.user.UserController
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class QueryResolver @Autowired constructor(
        private val userController: UserController,
        private val projectController: ProjectController
) : GraphQLQueryResolver {

    fun users()
            = userController.users()
}