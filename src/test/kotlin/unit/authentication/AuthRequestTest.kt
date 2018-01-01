package unit.authentication

import app.auhentication.AuthRequest
import app.auhentication.Encryption
import config.Config
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.servlet.http.HttpServletRequest

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Config::class])
class AuthRequestTest {

    private val request = Mockito.mock(HttpServletRequest::class.java)
    private val encryption = Mockito.mock(Encryption::class.java)
    private val authRequest = AuthRequest(encryption)

    @Test
    fun invalidJWTTokenReturnsNull() {
        Mockito.`when`(request.getHeader(AuthRequest.AUTHORIZATION_HEADER)).thenReturn("invalid")
        Assert.assertNull(authRequest.userIdFromRequest(request))
    }


    @Test
    fun validJWTTokenReturnsValueSent() {
        val value = "1"

        Mockito.`when`(request.getHeader(AuthRequest.AUTHORIZATION_HEADER))
                .thenReturn(value)
        Mockito.`when`(encryption.decrypt(value)).thenReturn(value)

        Assert.assertEquals(authRequest.userIdFromRequest(request), value)
    }


    @Test
    fun emptyRequestReturnsNull() {
        Mockito.`when`(request.getHeader(AuthRequest.AUTHORIZATION_HEADER)).thenReturn("")
        Assert.assertNull(authRequest.userIdFromRequest(request))
    }


}