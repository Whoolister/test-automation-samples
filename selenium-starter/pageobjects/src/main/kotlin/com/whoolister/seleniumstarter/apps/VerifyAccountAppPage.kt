package com.whoolister.seleniumstarter.apps

import com.whoolister.seleniumstarter.clickElement
import com.whoolister.seleniumstarter.getCount
import com.whoolister.seleniumstarter.getText
import com.whoolister.seleniumstarter.isDisplayed
import com.whoolister.seleniumstarter.isVisible
import com.whoolister.seleniumstarter.locators.byCss
import com.whoolister.seleniumstarter.locators.byId
import com.whoolister.seleniumstarter.locators.byTagName
import com.whoolister.seleniumstarter.onElementsIndexed
import com.whoolister.seleniumstarter.sendKeyToElement
import com.whoolister.seleniumstarter.windowSize
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.openqa.selenium.By
import org.openqa.selenium.Keys

object VerifyAccountAppPage: AppPage() {
    private val title: By = byId("title")
    private val message: By = byId("msg")
    private val codeInput: By = byCss("input[type='number'].code")
    private val hint: By = byTagName("small")

    private val successMessage: By = byCss(".info.success")

    private val hintRegex = Regex("""The confirmation code is (\d(-\d)*)""")

    private const val EXPECTED_TITLE = "Verify Your Account"

    override val shouldAppsButtonDisplayed: Boolean
        get() = windowSize.width >= 768
    override val shouldViewTestSuiteButtonDisplayed: Boolean = true

    override fun validate() {
        assertSoftly {
            validateNavbar()

            withClue("Title is '$EXPECTED_TITLE'") {
                getText(title) shouldBe EXPECTED_TITLE
            }
            withClue("Message is displayed") {
                isDisplayed(message) shouldBe true
            }
            withClue("Code input is displayed") {
                isDisplayed(codeInput) shouldBe true
            }
        }
    }

    fun keyUpCode(code: List<Int> = getCode()) = apply {
        val codeInputs = getCount(codeInput)

        withClue("Code inputs count ($codeInputs) matches code length") {
            codeInputs shouldBe code.size
        }

        onElementsIndexed(codeInput) { index ->
            clickElement()

            repeat(code[index]) {
                sendKeyToElement(Keys.ARROW_UP)
            }
        }
    }

    fun typeInCode(code: List<Int> = getCode()) = apply {
        val codeInputs = getCount(codeInput)

        withClue("Code inputs count ($codeInputs) matches code length") {
            codeInputs shouldBe code.size
        }

        onElementsIndexed(codeInput) { index ->
            sendKeyToElement(code[index].toString())
        }
    }

    fun validateSuccess() = apply {
        assertSoftly {
            withClue("Account verification was successful") {
                validateCodeInputs(areVisible = false)

                validateSuccessMessage(isDisplayed = true)
            }
        }
    }

    fun validateCodeInputs(areVisible: Boolean = true) = apply {
        withClue("Code inputs are ${if (areVisible) "" else "not "}visible") {
            isVisible(codeInput) shouldBe areVisible
        }
    }

    fun validateSuccessMessage(isDisplayed: Boolean = true) = apply {
        withClue("Success message is ${if (isDisplayed) "" else "not "}displayed") {
            isDisplayed(successMessage) shouldBe isDisplayed
        }
    }

    fun getHint(): String {
        val hintText = getText(hint)

        return hintRegex
            .find(hintText)
            ?.let { matchResult -> matchResult.groupValues[1] }
            ?: throw AssertionError("Hint text matches expected format: $hintText")
    }

    fun getCode(): List<Int> {
        val codeText = getHint()

        val code: List<Int> = codeText
            .split("-")
            .map(String::toInt)

        return code
    }

}