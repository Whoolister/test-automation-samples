package com.whoolister.seleniumstarter.locators

import org.intellij.lang.annotations.Language
import org.openqa.selenium.By

fun byCss(@Language("css") cssSelector: String) = By.cssSelector(cssSelector)

fun byCssAttribute(attribute: String, value: String) = byCss("[$attribute='$value']")

fun byCssAttributeContaining(attribute: String, value: String) = byCss("[$attribute*='$value']")