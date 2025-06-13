package com.whoolister.seleniumstarter.javascript.document

enum class DocumentReadyState {
    LOADING,
    INTERACTIVE,
    COMPLETE;

    companion object {
        fun from(value: String): DocumentReadyState? = when (value) {
            "loading" -> LOADING
            "interactive" -> INTERACTIVE
            "complete" -> COMPLETE
            else -> null
        }
    }
}