package com.whoolister.seleniumstarter.apps

import com.whoolister.seleniumstarter.click
import com.whoolister.seleniumstarter.getCount
import com.whoolister.seleniumstarter.getText
import com.whoolister.seleniumstarter.isDisplayed
import com.whoolister.seleniumstarter.isVisible
import com.whoolister.seleniumstarter.locators.byClassName
import com.whoolister.seleniumstarter.locators.byCss
import com.whoolister.seleniumstarter.locators.byId
import com.whoolister.seleniumstarter.locators.byTagName
import com.whoolister.seleniumstarter.locators.byXPath
import com.whoolister.seleniumstarter.locators.withParent
import com.whoolister.seleniumstarter.onElements
import com.whoolister.seleniumstarter.untilSuccess
import com.whoolister.seleniumstarter.windowSize
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import org.openqa.selenium.By

object MultiLevelDropdownPage: AppPage() {
    private val reactRoot: By = byId("root")
    private val dropdownNavbar: By = byTagName("nav") withParent reactRoot
    private val dropdownNavbarItem: By = byTagName("li") withParent dropdownNavbar

    private val dropdown: By = byClassName("dropdown")
    private val dropdownMenu: By = byCss(".menu") withParent dropdown
    private val dropdownMenuItem: By = byTagName("a") withParent dropdownMenu

    override val shouldAppsButtonDisplayed: Boolean
        get() = windowSize.width >= 768
    override val shouldViewTestSuiteButtonDisplayed: Boolean = true

    override fun validate() {
        assertSoftly {
            validateNavbar()

            withClue("Dropdown Navbar is displayed") {
                isDisplayed(dropdownNavbar) shouldBe true
            }
            withClue("Dropdown Navbar items are displayed") {
                getCount(dropdownNavbarItem) shouldBeGreaterThan 0
            }
        }
    }

    fun isDropdownMenuOpen(): Boolean = isVisible(dropdown)

    fun openDropdownMenu() = apply {
        if (!isDropdownMenuOpen()) clickDropdownNavbarItem(index = -1)
    }

    fun validateDropdownMenu(isOpen: Boolean = true) = apply {
        withClue("Dropdown menu is ${if (isOpen) "" else "not "}open") {
            isVisible(dropdown) shouldBe isOpen
        }
    }

    fun validateDropdownItem(item: DropdownItem, isVisible: Boolean = true) = apply {
        withClue("Dropdown item at position #${item.index} should ${if (isVisible) "" else "not "}have text such as '${item.text}'") {
            untilSuccess {
                if (isVisible) {
                    getText(dropdownMenuItem, index = item.index) shouldContain item.text
                } else {
                    getText(dropdownMenuItem, index = item.index) shouldNotContain item.text
                }
            } should { it.isSuccess }
        }
    }

    fun clickDropdownItem(item: DropdownItem) = apply {
        click(dropdownMenuItem, index = item.index)
    }

    fun clickDropdownNavbarItem(index: Int = 0) = apply {
        click(dropdownNavbarItem, index = index)
    }

    sealed class DropdownItem(val text: String, val index: Int) {
        data object MyProfile: DropdownItem("My Profile", index = 0)
        data object Settings: DropdownItem("Settings", index = 1) {
            data object MyTutorial: DropdownItem("My Tutorial", index = 0)
            data object HTML: DropdownItem("HTML", index = 1)
            data object CSS: DropdownItem("CSS", index = 2)
            data object JavaScript: DropdownItem("JavaScript", index = 3)
            data object Awesome: DropdownItem("Awesome!", index = 4)
        }
        data object Animals: DropdownItem("Animals", index = 2) {
            data object Animals: DropdownItem("Animals", index = 0)
            data object Kangaroo: DropdownItem("Kangaroo", index = 1)
            data object Frog: DropdownItem("Frog", index = 2)
            data object Horse: DropdownItem("Horse", index = 3)
            data object Hedgehog: DropdownItem("Hedgehog", index = 4)
        }
    }
}