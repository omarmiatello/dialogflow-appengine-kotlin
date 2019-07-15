package com.github.omarmiatello.actions.utils

import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

fun getResources(locale: Locale): ResourceBundle = ResourceBundle.getBundle("resources", locale)

val HttpServletRequest.bodyString get() = reader.readText()

val HttpServletRequest.headersMap get() = headerNames.asSequence().associateWith { getHeader(it) }

fun HttpServletResponse.write(string: String) = writer.write(string)


