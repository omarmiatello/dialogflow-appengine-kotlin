package com.github.omarmiatello.actions

import java.io.File


fun readText(fileName: String): String {
    return File("src/test/resources/$fileName").readText()
}