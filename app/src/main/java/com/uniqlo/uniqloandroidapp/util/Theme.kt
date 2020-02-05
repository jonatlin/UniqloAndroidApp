package com.uniqlo.uniqloandroidapp.util

enum class Theme(val key: String) {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system")
}

fun themeFromKey(key: String): Theme {

    return Theme.values().first {
        it.key == key
    }

}