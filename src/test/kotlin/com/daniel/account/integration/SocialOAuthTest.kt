package com.daniel.account.integration

import app.auhentication.SocialOAuth
import org.junit.Assert
import org.junit.Test

class SocialOAuthTest {

    @Test
    fun googleValidTokenReturnsValidUser() {
        // TODO Generate tokens automatically
        val token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjhiNmFkYjBjYWNmZmYyZTg2OTllNTIxNWZlZjA5YzBlODc2M2I1MmUifQ.eyJhenAiOiI4MzIwMjE4ODAyODQtcnZsbnVrcWVuaDB1bjUwZHFycWRwcTViM2FrYzJ2dW8uYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI4MzIwMjE4ODAyODQtcnZsbnVrcWVuaDB1bjUwZHFycWRwcTViM2FrYzJ2dW8uYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDgzNjU4MDM2NjM2NTg2MDA2NzciLCJlbWFpbCI6ImRhbnNwZWl4b3RvQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhdF9oYXNoIjoiYndFN1hRcHFvYnY1MkhZY253NXlJUSIsImlzcyI6ImFjY291bnRzLmdvb2dsZS5jb20iLCJqdGkiOiJhODJmZTJhYTRmOTE5ZDcwMDQ4M2QzMTA1NGFiNTE2NzNmNjBjMmZiIiwiaWF0IjoxNTE0ODU1ODQ2LCJleHAiOjE1MTQ4NTk0NDYsIm5hbWUiOiJEYW5pZWwgUGVpeG90byIsInBpY3R1cmUiOiJodHRwczovL2xoNS5nb29nbGV1c2VyY29udGVudC5jb20vLXMtUExiZ0Q1ZVpZL0FBQUFBQUFBQUFJL0FBQUFBQUFBQUQ0L3VzMDFJSjdZalNJL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJEYW5pZWwiLCJmYW1pbHlfbmFtZSI6IlBlaXhvdG8iLCJsb2NhbGUiOiJwdCJ9.PuFAnpCvanzUw39ZAxQO-ig2nOSb_9FitSOGHqyghZQClsBRmdkIzwO0mX9YwupFBPCV8FRX05F07mM6KIrnTarPc8sUH0Gz3RyLT-HOuGA8B8dZhYjJbf4GSnE63nYapfOBzQS0xpBMb8roGcwUUbxWmgsrWtIRsUBiviJwzhiYR0zK_02NWztToF1IbQTlhsN7bqTzfaWi_Ga2ZU1NUI8Q6Z0EN3D9QvWXtFkjIyhqs3RFaMvL3ksfZ22ofHf8RBsJ_f_FTuqNmuJia6WLfPTBosbdiBWXrRvh8NQ9f2-vY-EB_N4Eq0zNuafyNQaz4iyiouEp80qXzYJQNFysrA"

        val user = SocialOAuth.googleSignIn(token)

        Assert.assertNotNull(user)
        Assert.assertTrue(user!!.email.length > 3)
        Assert.assertTrue(!user.name.isEmpty())
    }

    @Test
    fun googleInvalidTokenReturnsNull() {
        val token = "1"

        val user = SocialOAuth.googleSignIn(token)

        Assert.assertNull(user)
    }

}