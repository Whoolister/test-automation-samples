package com.whoolister.seleniumstarter.apps

import com.whoolister.seleniumstarter.Navbar
import com.whoolister.seleniumstarter.Page

abstract class AppPage: Page, Navbar {

    override fun exit() = apply {
        clickOnAppsButton()
    }
}