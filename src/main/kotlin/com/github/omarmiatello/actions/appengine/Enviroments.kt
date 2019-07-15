package com.github.omarmiatello.actions.appengine

import com.google.appengine.api.utils.SystemProperty

val isAppEngineProduction
    get() = SystemProperty.environment.value() == SystemProperty.Environment.Value.Production

val isAppEngineDevelopment
    get() = SystemProperty.environment.value() == SystemProperty.Environment.Value.Development