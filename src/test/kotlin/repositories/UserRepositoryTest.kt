package repositories

import app.entities.user.User
import app.entities.user.UserRepository
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(
        classes = [app.Application::class]
)
class UserRepositoryTest {

    private val list = listOf(
            User("a@mail.com"),
            User("b@mail.com"),
            User("d@mail.com")
    )

    private val notInsertedUser = User(
            "notinserted@mail.com"
    )

    @Autowired
    lateinit var repository: UserRepository

    @Before
    fun init() {
        repository.deleteAll()
        repository.insert(list)
    }

    @Test
    fun insertedItemsCanBeRetrieved() {
        val list = repository.findAll()
        assert(this.list == list)
    }

    @Test
    fun notInsertedItemsCannotBeRetrieved() {
        val list = repository.findAll()
        assert(!list.contains(notInsertedUser))
    }

}