package com.whoolister.seleniumstarter

import co.touchlab.kermit.Logger.Companion.a
import com.whoolister.seleniumstarter.locators.byCss
import com.whoolister.seleniumstarter.locators.byCssAttribute
import com.whoolister.seleniumstarter.locators.byTagName
import com.whoolister.seleniumstarter.locators.byXPath
import com.whoolister.seleniumstarter.locators.withParent
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.openqa.selenium.By

interface Navbar {
    companion object {
        private val navbar: By = byCss("nav.topnav")
        private val navbarTitle: By = byTagName("span") withParent byCss(".item.logo")
        private val appsButton: By = byCssAttribute("aria-label", "full courses")
        private val viewTestSuiteButton: By = byXPath("//a[@class='item']")

        private const val EXPECTED_NAVBAR_TITLE = "QA Playground"
    }

    val shouldAppsButtonDisplayed: Boolean
    val shouldViewTestSuiteButtonDisplayed: Boolean

    fun validateNavbar() = apply {
        assertSoftly {
            withClue("Navbar is displayed") {
                isDisplayed(navbar) shouldBe true
            }
            withClue("Navbar title is $EXPECTED_NAVBAR_TITLE") {
                getNavbarTitle() shouldBe EXPECTED_NAVBAR_TITLE
            }
            withClue("Apps button is ${if (shouldAppsButtonDisplayed) "" else "not "}displayed") {
                isVisible(appsButton) shouldBe shouldAppsButtonDisplayed
            }
            withClue("View Test Suite button is ${if (shouldViewTestSuiteButtonDisplayed) "" else "not "}displayed") {
                isVisible(viewTestSuiteButton) shouldBe shouldViewTestSuiteButtonDisplayed
            }
        }
    }

    fun getNavbarTitle() = getText(navbarTitle)

    fun clickOnAppsButton() = apply {
        if (!shouldAppsButtonDisplayed) throw AssertionError("Apps button should be displayed")

        click(appsButton)
    }

    fun clickOnViewTestSuiteButton() = apply {
        if (!shouldViewTestSuiteButtonDisplayed) throw AssertionError("View Test Suite button should be displayed")

        click(viewTestSuiteButton)
    }
}