package com.whoolister.seleniumstarter.apps

import com.whoolister.seleniumstarter.BaseTest
import com.whoolister.seleniumstarter.PlaygroundLandingPage
import com.whoolister.seleniumstarter.on
import org.junit.jupiter.api.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class VerifyAccountTest: BaseTest(
    driverProvider = {
        val options = FirefoxOptions()
        options.addArguments("--headless")

        FirefoxDriver(options)
    },
    uri = "https://qaplayground.dev/",
) {

    @Test
    fun validateTypingInCorrectCode() {
        on(PlaygroundLandingPage)
            .clickMiniWebAppsButton()
            .validateMiniWebApp(PlaygroundLandingPage.MiniWebApp.VERIFY_YOUR_ACCOUNT)
            .clickOnMiniWebApp(PlaygroundLandingPage.MiniWebApp.VERIFY_YOUR_ACCOUNT)
            .on(VerifyAccountAppPage)
            .typeInCode()
            .validateSuccess()
    }

    @Test
    fun validateKeyingInCorrectCode() {
        on(PlaygroundLandingPage)
            .clickMiniWebAppsButton()
            .validateMiniWebApp(PlaygroundLandingPage.MiniWebApp.VERIFY_YOUR_ACCOUNT)
            .clickOnMiniWebApp(PlaygroundLandingPage.MiniWebApp.VERIFY_YOUR_ACCOUNT)
            .on(VerifyAccountAppPage)
            .keyUpCode()
            .validateSuccess()
    }
}