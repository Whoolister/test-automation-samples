package com.whoolister.seleniumstarter

import org.openqa.selenium.WebDriver
import java.net.URI

private val threadLocalDrivers = ThreadLocal<WebDriver>()

fun setDriver(webDriver: WebDriver) {
    if (threadLocalDrivers.get() != null) throw IllegalStateException("WebDriver already initialized for this thread")

    threadLocalDrivers.set(webDriver)
}

val driver: WebDriver
    get() = threadLocalDrivers.get() ?: throw IllegalStateException("WebDriver not initialized for this thread")

val currentUrl: String?
    get() = driver.currentUrl

val title: String?
    get() = driver.title

val pageSource: String?
    get() = driver.pageSource

val windowHandle: String
    get() = driver.windowHandle

val windowHandles: Set<String>
    get() = driver.windowHandles

val openWindows: Int
    get() = windowHandles.size

fun goTo(uri: URI) = driver.get(uri.toString())

fun closeWindow(force: Boolean = false) {
    check(windowHandles.size == 1 && !force) { "Attempted to close the last open window, without forcing." }
    driver.close()
}

fun quitDriver() {
    driver.quit()

    threadLocalDrivers.remove()
}

// TODO: Implement the following
// driver.navigate()
// driver.switchTo()