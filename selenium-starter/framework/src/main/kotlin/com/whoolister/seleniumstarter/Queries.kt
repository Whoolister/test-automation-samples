package com.whoolister.seleniumstarter

import jakarta.validation.constraints.PositiveOrZero
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.Rectangle
import org.openqa.selenium.WebElement
import kotlin.math.max

internal fun <T> ask(
    by: By,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    block: WebElement.() -> T,
): T = untilSuccess(timeoutInMillis) { onElement(by, index, block) }.getOrThrow()

internal fun <T> ask(
    by: By,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    block: WebElement.() -> T,
): List<T> = untilSuccess(timeoutInMillis) { onElements(by, block) }.getOrThrow()

context(webElement: WebElement)
internal fun <T> ask(
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    block: WebElement.() -> T,
): T = untilSuccess(timeoutInMillis) { block(webElement) }.getOrThrow()

fun getTagName(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String =
    ask(by, index, timeoutInMillis, WebElement::getTagName)

context(webElement: WebElement)
fun getElementTagName(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String =
    ask(timeoutInMillis, WebElement::getTagName)

fun getDomProperty(
    by: By,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    propertyName: String
): String? =
    ask(by, index, timeoutInMillis) { getDomProperty(propertyName) }

context(webElement: WebElement)
fun getElementDomProperty(propertyName: String, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String? =
    ask(timeoutInMillis) { getDomProperty(propertyName) }

fun getDomAttribute(
    by: By,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    attributeName: String
): String? =
    ask(by, index, timeoutInMillis) { getDomAttribute(attributeName) }

context(webElement: WebElement)
fun getElementDomAttribute(attributeName: String, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String? =
    ask(timeoutInMillis) { getDomAttribute(attributeName) }

fun getAttribute(
    by: By,
    attributeName: String,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS
): String? =
    ask(by, index, timeoutInMillis) { getAttribute(attributeName) }

context(webElement: WebElement)
fun getElementAttribute(attributeName: String, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String? =
    ask(timeoutInMillis) { getAttribute(attributeName) }

fun <T> getAttribute(
    by: By,
    attributeName: String,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    transform: (String?) -> T
): T =
    transform(getAttribute(by, attributeName, index, timeoutInMillis))

context(webElement: WebElement)
fun <T> getAttribute(
    attributeName: String,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    transform: (String?) -> T
): T = transform(getElementAttribute(attributeName, timeoutInMillis))

fun getAriaRole(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String? =
    ask(by, index, timeoutInMillis, WebElement::getAriaRole)

context(webElement: WebElement)
fun getElementAriaRole(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String? =
    ask(timeoutInMillis, WebElement::getAriaRole)

fun getAccessibleName(
    by: By,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS
): String? =
    ask(by, index, timeoutInMillis, WebElement::getAccessibleName)

context(webElement: WebElement)
fun getElementAccessibleName(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String? =
    ask(timeoutInMillis, WebElement::getAccessibleName)

fun isSelected(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Boolean =
    ask(by, index, timeoutInMillis, WebElement::isSelected)

context(webElement: WebElement)
fun isElementSelected(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Boolean =
    ask(timeoutInMillis, WebElement::isSelected)

fun isEnabled(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Boolean =
    ask(by, index, timeoutInMillis, WebElement::isEnabled)

context(webElement: WebElement)
fun isElementEnabled(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Boolean =
    ask(timeoutInMillis, WebElement::isEnabled)

fun getText(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String =
    ask(by, index, timeoutInMillis, WebElement::getText)

context(webElement: WebElement)
fun getElementText(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String =
    ask(timeoutInMillis, WebElement::getText)

fun getTexts(by: By, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): List<String> =
    ask(by, timeoutInMillis, WebElement::getText)

fun isDisplayed(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Boolean =
    untilSuccess(timeoutInMillis) { findElement(by, index) }.isSuccess

fun isVisible(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Boolean {
    val endTime = System.currentTimeMillis() + timeoutInMillis

    return isDisplayed(by, index, timeoutInMillis) && ask(by, index, max(0, endTime - System.currentTimeMillis()), WebElement::isDisplayed)
}

context(webElement: WebElement)
fun isVisible(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Boolean =
    ask(timeoutInMillis, WebElement::isDisplayed)

fun getLocation(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Point =
    ask(by, index, timeoutInMillis, WebElement::getLocation)

context(webElement: WebElement)
fun getElementLocation(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Point =
    ask(timeoutInMillis, WebElement::getLocation)

fun getSize(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Dimension =
    ask(by, index, timeoutInMillis, WebElement::getSize)

context(webElement: WebElement)
fun getElementSize(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Dimension =
    ask(timeoutInMillis, WebElement::getSize)

fun getRect(by: By, index: Int = 0, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Rectangle =
    ask(by, index, timeoutInMillis, WebElement::getRect)

context(webElement: WebElement)
fun getElementRect(@PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): Rectangle =
    ask(timeoutInMillis, WebElement::getRect)

fun getCssValue(
    by: By,
    propertyName: String,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS
): String =
    ask(by, index, timeoutInMillis) { getCssValue(propertyName) }

context(webElement: WebElement)
fun getElementCssValue(propertyName: String, @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS): String =
    ask(timeoutInMillis) { getCssValue(propertyName) }

fun <T> getCssValue(
    by: By,
    propertyName: String,
    index: Int = 0,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    transform: (String) -> T
): T =
    transform(getCssValue(by, propertyName, index, timeoutInMillis))

context(webElement: WebElement)
fun <T> getElementCssValue(
    propertyName: String,
    @PositiveOrZero timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS,
    transform: (String) -> T
): T = transform(getElementCssValue(propertyName, timeoutInMillis))

fun getCount(by: By): Int = findElements(by).size