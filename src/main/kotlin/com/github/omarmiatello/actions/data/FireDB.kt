package com.github.omarmiatello.actions.data

import com.github.omarmiatello.actions.appengine.FirebaseDatabaseApi
import com.github.omarmiatello.actions.appengine.fireMap
import com.github.omarmiatello.actions.appengine.fireProperty
import kotlinx.serialization.map
import kotlinx.serialization.serializer


object FireDB : FirebaseDatabaseApi() {
    override val basePath = "https://__project_name__.firebaseio.com/"

    var testString by fireProperty("testString", String.serializer(), useCache = true)
}