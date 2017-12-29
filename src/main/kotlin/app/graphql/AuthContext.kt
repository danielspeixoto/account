package app.graphql

import app.entities.user.User
import graphql.servlet.GraphQLContext
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthContext(
        request: Optional<HttpServletRequest>?,
        response: Optional<HttpServletResponse>?,
        val user: User? = null
) : GraphQLContext(request, response)