package com.whoolister.seleniumstarter

import com.whoolister.seleniumstarter.extensions.DriverExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.RegisterExtension
import org.openqa.selenium.WebDriver
import java.net.URI

abstract class BaseTest(driverProvider: () -> WebDriver, private val uri: URI? = null) {
    constructor(driverProvider: () -> WebDriver, uri: String) : this(driverProvider, URI(uri))

    @RegisterExtension
    val driverExtension = DriverExtension(driverProvider)

    @BeforeEach
    fun setUp() {
        if (uri == null) return

        goTo(uri)
    }
}