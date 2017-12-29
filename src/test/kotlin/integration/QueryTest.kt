package integration

import app.Application
import app.entities.user.User
import app.entities.user.UserRepository
import app.graphql.GraphQLEndpoint
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Application::class])
class QueryTest {

    private val list = listOf(
            User("a@mail.com"),
            User("b@mail.com"),
            User("d@mail.com")
    )

    // TODO Inserting data through a repository
    // assumes that it is working properly and can
    // make error tracking harder
    @Autowired
    lateinit var repository: UserRepository

    //    private val apollo
    @Before
    fun setUp() {
        repository.deleteAll()
        repository.insert(list)
    }

    @Autowired
    lateinit var endpoint: GraphQLEndpoint

    @Test
    fun queryUsersReturnsAllUsers() {
        Assert.assertEquals(endpoint.executeQuery("""
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