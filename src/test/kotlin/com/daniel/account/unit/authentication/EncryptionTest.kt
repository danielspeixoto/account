package app.unit.authentication

import app.auhentication.Encryption
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.springframework.core.env.Environment

class EncryptionTest {

    private val environment = Mockito.mock(Environment::class.java)
    private val encryption: Encryption
    private val mockedKey = "mocked key must be large"

    init {
        Mockito.`when`(environment.getProperty(Encryption.JWT_KEY))
                .thenReturn(mockedKey)
        encryption = Encryption(environment)
    }

    @Test
    fun decryptAndEncryptAreSync() {
        val id = "1"
        Assert.assertEquals(encryption
                .decrypt(encryption
                        .encrypt(id)), id)
    }
}