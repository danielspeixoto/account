package app.auhentication

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*


@Component
class Encryption @Autowired constructor(
        private val environment: Environment
) {

    companion object {
        val JWT_KEY = "JWT_KEY"
    }

    private val key = environment.getProperty(JWT_KEY)
    private val algorithm = SignatureAlgorithm.HS256
    // 7 days
    private val maxExpiration = Instant.now().plusMillis(1000 * 60 * 60 * 24 * 7)

    fun encrypt(subject: String,
                expiration: Date = Date.from(maxExpiration)): String {
        if (expiration > Date.from(maxExpiration)) {
            return encrypt(subject)
        }
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expiration)
                .signWith(algorithm, key)
                .compact()
    }

    fun decrypt(content: String): String {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(content).body.subject
    }
}