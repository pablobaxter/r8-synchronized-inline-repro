package com.example.r8repro

class Foobar {

    private val map = HashMap<String, Int>()

    @Synchronized
    fun doSomething(key: String) {
        val value = map[key]
        if (value == null) {
            map.remove(key)
        } else {
            map[key] = value + 1
        }
    }
}
