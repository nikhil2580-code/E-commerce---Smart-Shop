package com.nikhilkhairnar.shopsmart

import android.app.Application
import android.content.Context.MODE_PRIVATE

const val INTRODUCTION_SP = "IntroductionSP"
const val INTRODUCTION_KEY = "IntroductionKey"

class IntroductionSP(application: Application) {
    private val sharedPreferences = application.getSharedPreferences(INTRODUCTION_SP, MODE_PRIVATE)

    var isButtonClicked: Boolean
        get() = sharedPreferences.getBoolean(INTRODUCTION_KEY, false)
        set(value) {
            sharedPreferences.edit().putBoolean(INTRODUCTION_KEY, value).apply()
        }
}
