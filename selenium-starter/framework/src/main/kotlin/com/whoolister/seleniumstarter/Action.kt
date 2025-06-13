package com.whoolister.seleniumstarter

sealed class Action<T>(
    val description: String,
    val block: () -> T
): () -> T by block {
    override fun toString(): String = description
}