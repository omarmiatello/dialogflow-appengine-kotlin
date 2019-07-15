package com.github.omarmiatello.actions

import com.github.omarmiatello.actions.data.FireDB
import com.github.omarmiatello.actions.utils.EasyDialogflowApp
import com.google.actions.api.ActionRequest
import com.google.actions.api.ForIntent

class MyActionsApp : EasyDialogflowApp() {
    @ForIntent("Default Welcome Intent")
    fun welcome(request: ActionRequest) = request.toResponse {
        response.add(getString(if (request.user.isFirstAccess) "welcome" else "welcome_back"))
    }

    @ForIntent("bye")
    fun bye(request: ActionRequest) = request.toResponse {
        val testString = FireDB.testString
        response.add(getString("bye: $testString")).endConversation()
    }
}
