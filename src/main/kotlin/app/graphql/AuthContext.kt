package app.graphql

import graphql.servlet.GraphQLContext
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthContext(
        request: Optional<HttpServletRequest>?,
        response: Optional<HttpServletResponse>?,
        val userId: String? = null
) : GraphQLContext(request, response)