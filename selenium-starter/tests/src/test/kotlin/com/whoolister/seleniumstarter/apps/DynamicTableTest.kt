package com.whoolister.seleniumstarter.apps

import com.whoolister.seleniumstarter.BaseTest
import com.whoolister.seleniumstarter.PlaygroundLandingPage
import com.whoolister.seleniumstarter.on
import org.junit.jupiter.api.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class DynamicTableTest: BaseTest(
    driverProvider = {
        val options = FirefoxOptions()
        options.addArguments("--headless")

        FirefoxDriver(options)
    },
    uri = "https://qaplayground.dev/",
) {

    @Test
    fun peterParkerIsPresent() {
        on(PlaygroundLandingPage)
            .clickMiniWebAppsButton()
            .validateMiniWebApp(PlaygroundLandingPage.MiniWebApp.DYNAMIC_TABLE)
            .clickOnMiniWebApp(PlaygroundLandingPage.MiniWebApp.DYNAMIC_TABLE)
            .on(DynamicTableAppPage)
            .validateSuperhero("Spider-Man", "Peter Parker")
    }
}