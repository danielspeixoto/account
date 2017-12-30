package system

import app.Application
import app.entities.user.User
import app.entities.user.UserRepository
import app.graphql.GraphQLEndpoint
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.net.URL

@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [app.Application::class]
)
class QueryTest {

    @LocalServerPort
    private val port: Int = 0

    lateinit private var base: URL

    @Autowired
    private val template: TestRestTemplate? = null

    @Autowired
    lateinit var repository: UserRepository

    @Autowired
    lateinit var endpoint: GraphQLEndpoint

    private val list = listOf(
            User("a@mail.com"),
            User("b@mail.com"),
            User("d@mail.com")
    )

    @Before
    @Throws(Exception::class)
    fun setUp() {
        this.base = URL("http://localhost:$port/auth")
    }

    // TODO Inserting data through a repository
    // assumes that it is working properly and can
    // make error tracking harder
    @Test
    fun queryUsersReturnsAllUsers() {
        repository.deleteAll()
        repository.insert(list)
//        endpoint.servlet.addListener(object : GraphQLServletListener{
//            override fun onOperation(context: GraphQLContext?, operationName: String?, query: String?, variables: MutableMap<String, Any>?): GraphQLServletListener.OperationCallback {
//                println("Context")
//                println(context)
//                println(operationName)
//                println(query)
//                return super.onOperation(context, operationName, "", variables)
//            }
//
//            override fun onRequest(request: HttpServletRequest?, response: HttpServletResponse?): GraphQLServletListener.RequestCallback {
//                println("request")
//                println(request.toString())
//                println(response)
//                return super.onRequest(null, response)
//            }
//        })
        Assert.assertEquals(endpoint.servlet.executeQuery("""
            {
	            users {
                    email
                }
            }
            """),
                "{\"data\":{\"users\":[{\"email\":" +
                        "\"a@mail.com\"},{\"email\":\"" +
                        "b@mail.com\"},{\"email\":\"d@mail.com\"}]}}")
    }
}