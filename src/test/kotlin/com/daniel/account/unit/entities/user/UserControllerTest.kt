package app.unit.entities.user

import app.entities.project.Project
import app.entities.user.User
import app.entities.user.UserController
import app.entities.user.UserRepository
import com.daniel.account.config.Config
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Config::class])
class UserControllerTest {

    @Autowired
    lateinit private var repository: UserRepository
    lateinit private var controller: UserController

    private val userA = User("a@gmail.com", id = "1")
    private val userB = User("b@gmail.com", id = "2")
    private val userC = User("c@gmail.com", id = "3")

    private val projects = listOf(
            Project("a", "1", false, listOf("1", "2"), id = "1"),
            Project("b", "2", true, listOf("2"), id = "2"),
            Project("c", "1", false, listOf("1"), id = "3")
    )

    private val projectsId = listOf(
            "1", "2", "3"
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        controller = UserController(repository)
    }

    @Test
    fun user() {

    }
}