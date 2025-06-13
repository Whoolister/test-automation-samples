package com.whoolister.seleniumstarter

import com.whoolister.seleniumstarter.locators.byCss
import com.whoolister.seleniumstarter.locators.byTagName
import com.whoolister.seleniumstarter.locators.byXPath
import com.whoolister.seleniumstarter.locators.byXPathAttribute
import com.whoolister.seleniumstarter.locators.byXPathAttributeContaining
import com.whoolister.seleniumstarter.locators.withParent
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.openqa.selenium.By

object PlaygroundLandingPage: Page, Navbar {
    private val heroText: By = byTagName("h1") withParent byCss(".hero-content")
    private val heroIcon: By = byXPathAttribute("src", "grid6.svg")

    private val miniWebAppsButton: By = byXPathAttributeContaining("href", "/#apps")

    override val shouldAppsButtonDisplayed: Boolean = false
    override val shouldViewTestSuiteButtonDisplayed: Boolean
        get() = windowSize.width >= 1024 // View Test Suite button is only displayed on larger screens

    override fun validate() {
        assertSoftly {
            validateNavbar()

            withClue("Mini Web Apps are displayed") {
                isMiniWebAppDisplayed(MiniWebApp.DYNAMIC_TABLE) shouldBe true
            }
        }
    }

    fun validateHero() = apply {
        assertSoftly {
            withClue("Hero text is displayed") {
                isDisplayed(heroText) shouldBe true
            }
            withClue("Hero icon is displayed") {
                isDisplayed(heroIcon) shouldBe true
            }
        }
    }

    fun clickMiniWebAppsButton() = apply {
        click(miniWebAppsButton)
    }

    private fun isMiniWebAppDisplayed(miniWebApp: MiniWebApp): Boolean =
        isDisplayed(byXPathAttribute("href", miniWebApp.path))

    private fun getMiniWebAppTags(miniWebApp: MiniWebApp): List<String> = getTexts(byCss("span.tag.tag-sm") withParent byXPathAttribute("href", miniWebApp.path))

    fun validateMiniWebApp(miniWebApp: MiniWebApp) = apply {
        withClue("Mini Web App '$miniWebApp' is displayed") {
            isMiniWebAppDisplayed(miniWebApp) shouldBe true

            getMiniWebAppTags(miniWebApp) shouldContainAll miniWebApp.tags.toList()
        }
    }

    fun clickOnMiniWebApp(miniWebApp: MiniWebApp) = apply {
        click(byXPathAttribute("href", miniWebApp.path))
    }

    enum class MiniWebApp(val path: String, vararg val tags: String) {
        DYNAMIC_TABLE("/apps/dynamic-table/", "#html", "#tailwind", "#vanilla-javascript"),
        VERIFY_YOUR_ACCOUNT("/apps/verify-account/", "#html", "#vanilla-css", "#vanilla-javascript"),
        TAGS_INPUT_BOX("/apps/tags-input-box/", "#html", "#vanilla-css", "#vanilla-javascript"),
        MULTI_LEVEL_DROPDOWN("/apps/multi-level-dropdown/", "#reac", "#vanilla-css"),
    }
}

