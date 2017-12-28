package app.graphql

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
        query: Query,
        userResolver: UserResolver,
        projectResolver: ProjectResolver,
        private val userRepository : UserRepository
) : SimpleGraphQLServlet(
        SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(
                        query,
                        userResolver,
                        projectResolver
                )
                .build()
                .makeExecutableSchema()
) {

    override fun createContext(request: Optional<HttpServletRequest>?, response: Optional<HttpServletResponse>?): GraphQLContext {
        val user = request!!
                .map { req -> req.getHeader("Authorization") }
                .filter { id -> !id.isEmpty() }
                .map { id -> id.replace("Bearer ", "") }
                .map(userRepository::findById)
        .orElse(null)
        return AuthContext(User("ds"), request, response!!)
    }
}