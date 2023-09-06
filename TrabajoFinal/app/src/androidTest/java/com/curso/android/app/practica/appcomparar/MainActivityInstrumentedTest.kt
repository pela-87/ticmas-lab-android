package com.curso.android.app.practica.appcomparar

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.curso.android.app.practica.appcomparar.R.*
import com.curso.android.app.practica.appcomparar.view.MainActivity

import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    var rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun MainActivity_BtnComparar(){
        Espresso.onView(
            ViewMatchers.withId(R.id.btncomparar)
        ).perform(
            ViewActions.click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.txtComparacion)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Complete ambos campos para comparar.")
            )
        )
    }
}