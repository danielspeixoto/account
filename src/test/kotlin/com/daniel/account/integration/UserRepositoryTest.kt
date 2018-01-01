package com.daniel.account.integration

import app.Application
import app.entities.user.User
import app.entities.user.UserRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Application::class])
class UserRepositoryTest {

    private val list = listOf(
            User("a@mail.com", "a"),
            User("b@mail.com", "b"),
            User("d@mail.com", "c")
    )

    private val notInsertedUser = User(
            "notinserted@mail.com", "n"
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
        Assert.assertEquals(this.list, list)
    }

    @Test
    fun notInsertedItemsCannotBeRetrieved() {
        val list = repository.findAll()
        assert(!list.contains(notInsertedUser))
    }

}