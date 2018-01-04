package app.unit.authentication

import app.auhentication.Encryption
import com.daniel.account.config.UnitConfig
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [UnitConfig::class])
class EncryptionTest {

    @Autowired
    lateinit private var encryption: Encryption

    @Test
    fun decryptAndEncryptAreSync() {
        val id = "1"
        Assert.assertEquals(encryption
                .decrypt(encryption
                        .encrypt(id)), id)
    }
}