package com.whoolister.seleniumstarter.locators

import org.intellij.lang.annotations.Language
import org.openqa.selenium.By
import org.openqa.selenium.support.ByIdOrName
import org.openqa.selenium.support.pagefactory.ByChained

fun byId(id: String) = By.id(id)

fun byClassName(className: String) = By.className(className)

fun byName(name: String) = By.name(name)

fun byIdOrName(idOrName: String) = ByIdOrName(idOrName)

fun byTagName(tagName: String) = By.tagName(tagName)

fun byLinkText(linkText: String) = By.linkText(linkText)

fun byPartialLinkText(partialLinkText: String) = By.partialLinkText(partialLinkText)

infix fun By.withParent(parent: By): By = ByChained(parent, this)