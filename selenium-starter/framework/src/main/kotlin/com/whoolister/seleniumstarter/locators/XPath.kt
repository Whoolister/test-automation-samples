package com.whoolister.seleniumstarter.locators

import org.intellij.lang.annotations.Language
import org.openqa.selenium.By

fun byXPath(@Language("xpath") xpath: String) = By.xpath(xpath)

fun byXPathAttribute(attribute: String, value: String) = byXPath("//*[@$attribute='$value']")

fun byXpathAttribute(attribute: String, regex: Regex) = byXPath("//*[matches(@$attribute, '$regex')]")
fun byXPathAttributeContaining(attribute: String, value: String) = byXPath("//*[contains(@$attribute, '$value')]")
