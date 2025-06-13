package com.whoolister.seleniumstarter

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

internal fun findElements(by: By): List<WebElement> = driver.findElements(by)

internal fun findElement(by: By, index: Int = 0): WebElement = with(findElements(by)) {
    if (index < 0) this[size + index] else this[index]
}

internal fun WebElement.findChildren(by: By): List<WebElement> =
    findElements(by)

internal fun WebElement.findChild(by: By, index: Int = 0): WebElement =
    with(findElements(by)) {
        if (index < 0) this[size + index] else this[index]
    }

internal fun findChildren(source: By, by: By, sourceIndex: Int = 0): List<WebElement> =
    findElement(source, sourceIndex).findChildren(by)

internal fun findChild(source: By, by: By, sourceIndex: Int = 0, index: Int = 0): WebElement =
    with(findChildren(source, by, sourceIndex)) {
        if (index < 0) this[size + index] else this[index]
    }

fun <T> onElements(by: By, block: WebElement.() -> T): List<T> = findElements(by).map(block)

fun <T> onElementsIndexed(by: By, block: WebElement.(Int) -> T): List<T> =
    findElements(by).mapIndexed { index, element ->
        element.block(index)
    }

fun <T> onElement(
    by: By,
    index: Int = 0,
    block: WebElement.() -> T,
): T = block(findElement(by, index))

fun <T> onChildren(
    source: By,
    by: By,
    sourceIndex: Int = 0,
    block: WebElement.() -> T,
): List<T> = findChildren(source, by, sourceIndex).map(block)

fun <T> onChild(
    source: By,
    by: By,
    sourceIndex: Int = 0,
    index: Int = 0,
    block: WebElement.() -> T,
): T = block(findChild(source, by, sourceIndex, index))

context(webElement: WebElement)
fun <T> onChildren(
    by: By,
    block: WebElement.() -> T,
): List<T> = webElement.findChildren(by).map(block)

context(webElement: WebElement)
fun <T> onChild(
    by: By,
    index: Int = 0,
    block: WebElement.() -> T,
): T = block(webElement.findChild(by, index))