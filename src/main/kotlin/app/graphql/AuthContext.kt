package app.graphql

import app.entities.user.User
import graphql.servlet.GraphQLContext
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthContext(
        val user : User?,
        request : Optional<HttpServletRequest>,
        response : Optional<HttpServletResponse>
) : GraphQLContext(request, response)