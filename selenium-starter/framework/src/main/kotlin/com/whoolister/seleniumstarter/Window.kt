package com.whoolister.seleniumstarter

import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver

val window: WebDriver.Window
    get() = options.window()

var windowSize: Dimension
    get() = window.size
    set(value) {
        window.size = value
    }

var windowPosition: Point
    get() = window.position
    set(value) {
        window.position = value
    }

fun maximizeWindow() {
    window.maximize()
}

fun minimizeWindow() {
    window.minimize()
}

fun fullscreenWindow() {
    window.fullscreen()
}