package com.whoolister.seleniumstarter

import jakarta.validation.constraints.PositiveOrZero
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

internal fun perform(
    by: By,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    block: WebElement.() -> Unit,
) = untilSuccess(
    timeoutInMillis,
) { onElement(by, index, block) } .getOrThrow()

context(webElement: WebElement)
internal fun perform(
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    block: WebElement.() -> Unit,
) = untilSuccess(timeoutInMillis) { block(webElement) }.getOrThrow()

fun click(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(by, index, timeoutInMillis, WebElement::click)

context(webElement: WebElement)
fun clickElement(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(timeoutInMillis, WebElement::click)

fun submit(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(by, index, timeoutInMillis, WebElement::submit)

context(webElement: WebElement)
fun submitElement(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(timeoutInMillis, WebElement::submit)

fun sendKeys(
    by: By,
    vararg keys: CharSequence,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS
) = perform(by, index, timeoutInMillis) { sendKeys(*keys) }

context(webElement: WebElement)
fun sendKeyToElement(vararg keys: CharSequence, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(timeoutInMillis) { sendKeys(*keys) }

fun clear(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(by, index, timeoutInMillis, WebElement::clear)

context(webElement: WebElement)
fun clearElement(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(timeoutInMillis, WebElement::clear)

fun setText(by: By, text: String, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(by, index, timeoutInMillis) {
        clear()

        sendKeys(text)
    }

context(webElement: WebElement)
fun setElementText(text: String, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) =
    perform(timeoutInMillis) {
        clear()

        sendKeys(text)
    }