package com.example.r8repro

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : Activity() {

    private val foobar = Foobar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            foobar.doSomething("a")
        } catch (e: Exception) {
            Log.d("MainActivity", "Some error", e)
        }
        setContentView(TextView(this).apply { text = "Hello World"; setPadding(48, 48, 48, 48) })
    }
}
