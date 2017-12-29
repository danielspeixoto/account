package app.entities.user

import app.entities.project.ProjectController
import app.graphql.AuthContext
import com.coxautodev.graphql.tools.GraphQLResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserResolver @Autowired constructor(
        private val userController : UserController,
        private val projectController : ProjectController
) : GraphQLResolver<User> {

    fun projects(user : User, env : DataFetchingEnvironment) =
            projectController.projects(user.projectsId,
                    env.getContext<AuthContext>().user)

}