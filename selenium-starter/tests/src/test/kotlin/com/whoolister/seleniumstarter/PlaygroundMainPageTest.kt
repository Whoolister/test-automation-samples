package com.whoolister.seleniumstarter

import org.junit.jupiter.api.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class PlaygroundMainPageTest: BaseTest(
    driverProvider = {
        val options = FirefoxOptions()
        options.addArguments("--headless")

        FirefoxDriver(options)
    },
    uri = "https://qaplayground.dev/",
) {

    @Test
    fun navbarIsDisplayed() {
        on(PlaygroundLandingPage)
            .validateNavbar()
    }

    @Test
    fun heroIsDisplayed() {
        on(PlaygroundLandingPage)
            .validateHero()
    }

    @Test
    fun miniWebAppsButtonIsClickable() {
        on(PlaygroundLandingPage)
            .clickMiniWebAppsButton()
            .validateMiniWebApp(PlaygroundLandingPage.MiniWebApp.DYNAMIC_TABLE)
    }
}

