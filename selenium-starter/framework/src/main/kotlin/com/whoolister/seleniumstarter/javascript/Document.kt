package com.whoolister.seleniumstarter.javascript

import com.whoolister.seleniumstarter.javascript.document.DocumentReadyState

val documentReadyState: DocumentReadyState?
    get() {
        val rawValue = executeJavaScript("return document.readyState").toString()

        return DocumentReadyState.from(rawValue)
    }

fun isDocumentReady(): Boolean = documentReadyState == DocumentReadyState.COMPLETE

