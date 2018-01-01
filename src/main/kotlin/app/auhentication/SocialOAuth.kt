package app.auhentication

import app.entities.user.User
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.json.jackson2.JacksonFactory

object SocialOAuth {

    // TODO Add verification to check from which client the token came from

    fun googleSignIn(tokenStr: String): User? {
        try {
            val idToken = GoogleIdToken.parse(JacksonFactory.getDefaultInstance(), tokenStr)
            idToken?.let {
                val payload = idToken.payload
                return User(payload)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}