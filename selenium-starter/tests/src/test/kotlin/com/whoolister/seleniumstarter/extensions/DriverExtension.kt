package com.whoolister.seleniumstarter.extensions

import com.whoolister.seleniumstarter.quitDriver
import com.whoolister.seleniumstarter.setDriver
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.openqa.selenium.WebDriver

class DriverExtension(val driverProvider: () -> WebDriver): BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext) {
        setDriver(driverProvider())
    }

    override fun afterEach(context: ExtensionContext) {
        quitDriver()
    }
}