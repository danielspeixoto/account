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

    @Test(timeout = 5000)
    fun queryUsersReturnsAllUsers() {
        repository.insert(list)
        apollo.query(
                UserQuery.builder().build()
        ).enqueue(object : ApolloCall.Callback<UserQuery.Data>() {
            override fun onFailure(e: ApolloException) {
                Assert.fail()
            }

            override fun onResponse(response: Response<UserQuery.Data>) {
                val result = response.data()!!.users()!!.map {
                    User(it.email(), id = it.id())
                }
                Assert.assertEquals(list, result)
            }


        })

        // TODO Try better way to test async code
        Thread.sleep(2000)
    }
}