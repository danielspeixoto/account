package app.graphql

import app.auhentication.AuthRequest
import app.entities.project.ProjectResolver
import app.entities.user.User
import app.entities.user.UserRepository
import app.entities.user.UserResolver
import com.coxautodev.graphql.tools.SchemaParser
import graphql.servlet.GraphQLContext
import graphql.servlet.SimpleGraphQLServlet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class GraphQLEndpoint @Autowired constructor(
        queryResolver: QueryResolver,
        userResolver: UserResolver,
        projectResolver: ProjectResolver,
        private val userRepository: UserRepository,
        private val authRequest: AuthRequest
) {

    val servlet = SimpleGraphQLServlet.builder(SchemaParser.newParser()
            .file("schema.graphqls")
            .resolvers(
                    queryResolver,
                    userResolver,
                    projectResolver
            )
            .build()
            .makeExecutableSchema())
            .withGraphQLContextBuilder(::createContext)
            .build()!!

    // Receives a Encrypted Token and maps it to a User
    fun createContext(request: Optional<HttpServletRequest>,
                      response: Optional<HttpServletResponse>): GraphQLContext {
        var user: User? = null
        request.ifPresent {
            val userId = authRequest.userIdFromRequest(request.get())
            userId?.let {
                user = userRepository.findById(userId)
            }
        }
        return AuthContext(request, response, user)
    }


}