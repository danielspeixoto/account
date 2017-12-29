package app.auhentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class AuthRequest @Autowired constructor(
        private val encryption: Encryption
) {

    companion object {
        val AUTHORIZATION_HEADER = "Authorization"
    }

    fun userIdFromRequest(request: HttpServletRequest): String? {
        val authHeader = request.getHeader(AUTHORIZATION_HEADER)
        if (!authHeader.isEmpty()) {
            return encryption.decrypt(authHeader)
        }
        return null
    }


}