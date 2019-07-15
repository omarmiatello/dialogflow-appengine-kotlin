package com.github.omarmiatello.actions.utils

import com.google.actions.api.ActionRequest
import com.google.actions.api.DefaultApp
import com.google.actions.api.DialogflowApp
import com.google.api.services.actions_fulfillment.v2.model.User


open class EasyDialogflowApp : DialogflowApp() {
    fun ActionRequest.toResponse(block: DialogflowResponseBuilder.() -> Unit) =
            DialogflowResponseBuilder(this@EasyDialogflowApp, this)
                    .apply(block)
                    .run { response.build() }
}

class DialogflowResponseBuilder(app: DefaultApp, request: ActionRequest) {
    val response = app.getResponseBuilder(request)

    private val resources by lazy { getResources(request.locale) }

    // Resources

    fun getString(key: String): String = resources.getString(key)

    // User

    inline val User?.isFirstAccess get() = this?.lastSeen == null
}