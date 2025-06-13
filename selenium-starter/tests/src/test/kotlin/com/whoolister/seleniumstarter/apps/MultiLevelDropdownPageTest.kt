package com.whoolister.seleniumstarter.apps

import com.whoolister.seleniumstarter.BaseTest
import com.whoolister.seleniumstarter.PlaygroundLandingPage
import com.whoolister.seleniumstarter.on
import org.junit.jupiter.api.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class MultiLevelDropdownPageTest: BaseTest(
    driverProvider = {
        val options = FirefoxOptions()
        options.addArguments("--headless")

        FirefoxDriver(options)
    },
    uri = "https://qaplayground.dev/",
) {

    @Test
    fun validateDropdownMenuOpens() {
        on(PlaygroundLandingPage)
            .clickMiniWebAppsButton()
            .validateMiniWebApp(PlaygroundLandingPage.MiniWebApp.MULTI_LEVEL_DROPDOWN)
            .clickOnMiniWebApp(PlaygroundLandingPage.MiniWebApp.MULTI_LEVEL_DROPDOWN)
            .on(MultiLevelDropdownPage)
            .clickDropdownNavbarItem(index = -1)
            .validateDropdownMenu(isOpen = true)
    }

    @Test
    fun validateMyProfileDropdownOption() {
        on(PlaygroundLandingPage)
            .clickMiniWebAppsButton()
            .validateMiniWebApp(PlaygroundLandingPage.MiniWebApp.MULTI_LEVEL_DROPDOWN)
            .clickOnMiniWebApp(PlaygroundLandingPage.MiniWebApp.MULTI_LEVEL_DROPDOWN)
            .on(MultiLevelDropdownPage)
            .openDropdownMenu()
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.MyProfile)
            .clickDropdownItem(MultiLevelDropdownPage.DropdownItem.MyProfile)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.MyProfile)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Animals)
    }

    @Test
    fun validateSettingsDropdownOption() {
        on(PlaygroundLandingPage)
            .clickMiniWebAppsButton()
            .validateMiniWebApp(PlaygroundLandingPage.MiniWebApp.MULTI_LEVEL_DROPDOWN)
            .clickOnMiniWebApp(PlaygroundLandingPage.MiniWebApp.MULTI_LEVEL_DROPDOWN)
            .on(MultiLevelDropdownPage)
            .openDropdownMenu()
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings)
            .clickDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings.MyTutorial)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings.HTML)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings.CSS)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings.JavaScript)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings.Awesome)
    }
    @Test
    fun validateAnimalsDropdownOption() {
        on(PlaygroundLandingPage)
            .clickMiniWebAppsButton()
            .validateMiniWebApp(PlaygroundLandingPage.MiniWebApp.MULTI_LEVEL_DROPDOWN)
            .clickOnMiniWebApp(PlaygroundLandingPage.MiniWebApp.MULTI_LEVEL_DROPDOWN)
            .on(MultiLevelDropdownPage)
            .openDropdownMenu()
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Settings)
            .clickDropdownItem(MultiLevelDropdownPage.DropdownItem.Animals)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Animals.Animals)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Animals.Kangaroo)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Animals.Frog)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Animals.Horse)
            .validateDropdownItem(MultiLevelDropdownPage.DropdownItem.Animals.Hedgehog)
    }
}