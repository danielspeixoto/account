package unit.graphql

import config.Config
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Config::class])
class GraphQLEndpointTest {

    @Test
    fun invalidJWTTokenReturnsNull() {

    }

    @Test
    fun validJWTTokenReturnsCorrectUser() {

    }
}