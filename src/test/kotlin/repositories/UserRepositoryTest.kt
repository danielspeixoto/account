package repositories

import app.entities.user.User
import app.entities.user.UserRepository
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
class UserRepositoryTest {

    private val list = listOf(
            User("a@gmail.com"),
            User("b@gmail.com"),
            User("d@gmail.com")
    )

    @Autowired
    var repository: UserRepository? = null

    @Before
    fun init() {
        repository!!.deleteAll()
        repository!!.insert(list)
    }

    @Test
    fun happyTest() {
        val list = repository!!.findAll()
        assert(this.list.equals(list))
    }

}