package com.daniel.account.system

import app.Application
import app.entities.user.User
import app.entities.user.UserRepository
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.daniel.account.UserQuery
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [Application::class]
)
class QueryTest {

    @LocalServerPort
    lateinit var port: String
    lateinit var url: String

    @Autowired
    lateinit var repository: UserRepository
    lateinit private var apollo: ApolloClient

    private val list = listOf(
            User("a@mail.com"),
            User("b@mail.com"),
            User("d@mail.com")
    )

    companion object {
        private val TIMEOUT: Long = 2_000
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        url = "http://localhost:$port/graphql"
        apollo = ApolloClient.builder()
                .serverUrl(url)
                .okHttpClient(OkHttpClient())
                .build()
        repository.deleteAll()
    }

    @Test
    fun queryUsersReturnsAllUsers() {
        // SETUP
        val countdown = CountDownLatch(1)
        var result: Response<UserQuery.Data>? = null

        // ACT
        repository.insert(list)
        apollo.query(
                UserQuery.builder().build()
        ).enqueue(object : ApolloCall.Callback<UserQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                e.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(response: Response<UserQuery.Data>) {
                result = response
                countdown.countDown()
            }


        })

        // ASSERT
        // Waits TIMEOUT millis
        countdown.await(TIMEOUT, TimeUnit.MILLISECONDS)
        if (result == null) {
            Assert.fail()
        }

        val mappedUsers = result!!.data()!!.users()!!.map {
            User(it.email(), id = it.id())
        }
        Assert.assertEquals(list, mappedUsers)
    }
}