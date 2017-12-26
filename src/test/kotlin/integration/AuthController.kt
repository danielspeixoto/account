package integration

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat

import java.net.URL

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [app.Application::class]
)
class HelloControllerTest {

    @LocalServerPort
    private val port: Int = 0

    private var base: URL? = null

    @Autowired
    private val template: TestRestTemplate? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        this.base = URL("http://localhost:$port/auth")
    }

    @Test
    @Throws(Exception::class)
    fun auth_correctInput_returnsValidAuth() {
        val response = template!!.getForEntity(base!!.toString(),
                String::class.java)
        assertThat(response.body, equalTo("Auth"))
    }
}