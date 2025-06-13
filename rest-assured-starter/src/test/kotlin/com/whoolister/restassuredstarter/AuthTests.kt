package com.whoolister.restassuredstarter

import com.whoolister.restassuredstarter.models.auth.AuthRequest
import com.whoolister.restassuredstarter.models.auth.AuthResponse
import io.kotest.assertions.withClue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldHaveMinLength
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.Test

class AuthTests {
    private val username = "admin"
    private val password = "password123"
    private val request: AuthRequest = AuthRequest(username, password)

    @Test
    fun createToken() {
        val authResponse: AuthResponse = Given {
            port(3001)
            contentType("application/json")
            body(request)
        } When {
            post("/auth")
        } Then {
            statusCode(200)
        } Extract {
            `as`(AuthResponse::class.java)
        }

        withClue("Authentication response should contain a non-empty token") {
            authResponse shouldNotBeNull {
                token shouldHaveMinLength 1
            }
        }
    }
}