package com.daniel.account.unit.graphql

import app.auhentication.AuthRequest
import app.entities.project.ProjectResolver
import app.entities.user.UserRepository
import app.entities.user.UserResolver
import app.graphql.AuthContext
import app.graphql.GraphQLEndpoint
import app.graphql.QueryResolver
import com.daniel.account.config.UnitConfig
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [UnitConfig::class])
class GraphQLEndpointTest {

    @Autowired
    lateinit private var queryResolver: QueryResolver
    @Autowired
    lateinit private var userResolver: UserResolver
    @Autowired
    lateinit private var projectResolver: ProjectResolver

    @Mock
    lateinit private var userRepository: UserRepository
    @Mock
    lateinit private var authRequest: AuthRequest

    lateinit private var endpoint: GraphQLEndpoint

    @Mock
    lateinit private var request: HttpServletRequest
    @Mock
    lateinit private var response: HttpServletResponse
    lateinit private var optionalRequest: Optional<HttpServletRequest>
    lateinit private var optionalResponse: Optional<HttpServletResponse>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        endpoint = GraphQLEndpoint(
                queryResolver, userResolver,
                projectResolver, userRepository,
                authRequest
        )
    }

    @Test
    fun nullFromRequestImpliesContextNullUser() {
        optionalRequest = Optional.of(request)
        optionalResponse = Optional.of(response)
        Mockito.`when`(
                authRequest.userIdFromRequest(request))
                .thenReturn(null)

        val result = endpoint.createContext(optionalRequest, optionalResponse)

        Assert.assertNull((result as AuthContext).userId)
    }

    @Test
    fun userFromRequestImpliesContextWithUser() {
        optionalRequest = Optional.of(request)
        optionalResponse = Optional.of(response)
        val value = "1"
        Mockito.`when`(
                authRequest.userIdFromRequest(request))
                .thenReturn(value)

        val result = endpoint.createContext(optionalRequest, optionalResponse)
        Assert.assertEquals(value, (result as AuthContext).userId)
    }
}