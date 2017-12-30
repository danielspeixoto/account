package com.account.unit.authentication

import app.auhentication.Encryption
import com.account.config.Config
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [Config::class])
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