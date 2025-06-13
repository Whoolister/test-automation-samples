package com.whoolister.restassuredstarter

import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test


class PingTests {
    @Test
    fun healthCheckTest() {
        Given {
            port(3001)
        } When {
            get("/ping")
        } Then {
            statusCode(201)
            body(equalTo("Created"))
        }
    }
}