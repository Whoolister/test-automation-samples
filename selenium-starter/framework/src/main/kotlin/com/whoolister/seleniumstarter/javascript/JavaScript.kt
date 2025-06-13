package com.whoolister.seleniumstarter.javascript

import com.whoolister.seleniumstarter.driver
import org.intellij.lang.annotations.Language
import org.openqa.selenium.JavascriptExecutor

val javascriptExecutor: JavascriptExecutor
    get() = driver as JavascriptExecutor

fun executeJavaScript(
    @Language("javascript") script: String,
    vararg args: Any,
): Any? = javascriptExecutor.executeScript(script)
