package com.whoolister.seleniumstarter.apps

import com.whoolister.seleniumstarter.getElementText
import com.whoolister.seleniumstarter.isDisplayed
import com.whoolister.seleniumstarter.locators.byCss
import com.whoolister.seleniumstarter.locators.byTagName
import com.whoolister.seleniumstarter.locators.withParent
import com.whoolister.seleniumstarter.onChild
import com.whoolister.seleniumstarter.onElements
import com.whoolister.seleniumstarter.windowSize
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.openqa.selenium.By

object DynamicTableAppPage: AppPage() {
    private val table: By = byTagName("table")
    private val tableHeader: By = byTagName("thead")
    private val tableBody: By = byTagName("tbody")
    private val tableBodyRow: By = byTagName("tr") withParent tableBody
    private val rowSuperheroName: By = byCss(".text-sm.font-medium") withParent byTagName("td")
    private val rowEmail: By = byCss(".text-sm.text-gray-500") withParent byTagName("td")
    private val rowStatus: By = byCss("span.font-semibold") withParent byTagName("td")
    private val rowRealName: By = byCss("span.font-medium") withParent byTagName("td")

    override val shouldAppsButtonDisplayed: Boolean
        get() = windowSize.width >= 768
    override val shouldViewTestSuiteButtonDisplayed: Boolean = true

    override fun validate() {
        assertSoftly {
            validateNavbar()

            withClue("Table is displayed") {
                isDisplayed(table) shouldBe true
            }
            withClue("Table header is displayed") {
                isDisplayed(tableHeader) shouldBe true
            }
            withClue("Table body is displayed") {
                isDisplayed(tableBody) shouldBe true
            }
            withClue("Table row is displayed") {
                withClue("Row superhero name is displayed") {
                    isDisplayed(rowSuperheroName) shouldBe true
                }
                withClue("Row email is displayed") {
                    isDisplayed(rowEmail) shouldBe true
                }
                withClue("Row status is displayed") {
                    isDisplayed(rowStatus) shouldBe true
                }
                withClue("Row real name is displayed") {
                    isDisplayed(rowRealName) shouldBe true
                }
            }
        }
    }

    fun validateSuperhero(superheroName: String, expectedRealName: String) = apply {
        val superhero = getTableRows().find { it.superheroName == superheroName }
        withClue("Superhero '$superheroName' found in the table") {
            superhero shouldNotBeNull {
                withClue("Real name '$expectedRealName' matches for superhero '$superheroName'") {
                    realName shouldBe expectedRealName
                }
            }
        }
    }

    private fun getTableRows(): List<TableRow> = onElements(tableBodyRow) {
        val superheroName: String = onChild(rowSuperheroName) { getElementText() }

        val email: String = onChild(rowEmail) { getElementText() }

        val status: String = onChild(rowStatus) { getElementText() }

        val realName: String = onChild(rowRealName) { getElementText() }

        TableRow(superheroName, email, status, realName)
    }

    private data class TableRow(
        val superheroName: String,
        val email: String,
        val status: String,
        val realName: String,
    )
}