package app.auhentication

import app.helpers.Convert
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*


@Component
class Encryption {

    @Value("\${JWT}")
    lateinit private var key: String
    private val algorithm = SignatureAlgorithm.HS256
    private val maxExpiration = Date.from(
            Instant.now()
                    .plusMillis(
                            Convert.daysToMillis(8)))

    fun encrypt(subject: String,
                expiration: Date = maxExpiration): String {
        if (expiration > maxExpiration) {
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