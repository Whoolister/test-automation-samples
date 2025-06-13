package com.whoolister.seleniumstarter

import com.whoolister.seleniumstarter.javascript.document.DocumentReadyState
import com.whoolister.seleniumstarter.javascript.documentReadyState
import com.whoolister.seleniumstarter.javascript.isDocumentReady

fun <T: Page> on(page: T): T {
    page.validate()

    return page
}

interface Page {

    fun <T: Page> on(page: T): T = com.whoolister.seleniumstarter.on(page)

    fun validate()

    fun exit() = apply {
        // Specific pages can override the default exit implementation if needed
        throw NotImplementedError("Exit is not supported for this page")
    }

    fun waitUntilDocumentReady(timeoutInMillis: Long = DEFAULT_TIMEOUT_IN_MILLIS) = untilTrue(timeoutInMillis, condition = ::isDocumentReady)
}