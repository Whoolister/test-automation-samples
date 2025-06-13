package com.whoolister.seleniumstarter

import org.openqa.selenium.Cookie
import org.openqa.selenium.WebDriver

val options: WebDriver.Options
    get() = driver.manage()

fun addCookie(cookie: Cookie) = options.addCookie(cookie)

fun deleteCookieNamed(name: String) = options.deleteCookieNamed(name)

fun deleteCookie(cookie: Cookie) = options.deleteCookie(cookie)

fun deleteAllCookies() = options.deleteAllCookies()

fun getCookieNamed(name: String): Cookie? = options.getCookieNamed(name)

fun getCookies(): Set<Cookie> = options.cookies