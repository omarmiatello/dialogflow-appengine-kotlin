package com.github.omarmiatello.actions.ktx

import com.google.actions.api.ActionContext
import com.google.actions.api.response.ResponseBuilder
import com.google.actions.api.response.helperintent.SelectionList
import com.google.api.services.actions_fulfillment.v2.model.*


fun ResponseBuilder.addSuggestions(vararg suggestions: String) = addSuggestions(suggestions.distinct().toTypedArray())

fun ResponseBuilder.addSimpleResponse(
        displayText: String,
        textToSpeech: String? = null,
        ssml: String? = null
) = add(SimpleResponse().also {
    // Specs: https://developers.google.com/actions/assistant/responses#simple_response
    it.displayText = displayText
    if (ssml != null) {
        it.ssml = ssml
    } else {
        it.textToSpeech = textToSpeech ?: displayText
    }
})

fun ResponseBuilder.addSelectionList(
        title: String,
        items: List<ListSelectListItem>
) = add(SelectionList().also {
    // Specs: https://developers.google.com/actions/assistant/responses#list
    it.setTitle(title)
    it.setItems(items)
})

fun ResponseBuilder.addActionContext(
        name: String,
        lifespan: Int? = 5,
        parameters: Map<String, Any>? = null
) = add(ActionContext(name, lifespan).also {
    it.parameters = parameters
})

fun ResponseBuilder.addBasicCard(
        title: String? = null,
        subtitle: String? = null,
        formattedText: String? = null,
        image: Image? = null,
        imageDisplayOptions: String? = null,
        buttons: MutableList<Button>? = null
) = add(BasicCard().also {
    // Specs: https://developers.google.com/actions/assistant/responses#basic_card
    if (formattedText == null && image == null) error("Missing required fields: formattedText OR image")
    if (title != null) it.title = title
    if (subtitle != null) it.subtitle = subtitle
    if (formattedText != null) it.formattedText = formattedText
    if (image != null) it.image = image
    if (imageDisplayOptions != null) it.imageDisplayOptions = imageDisplayOptions
    if (buttons != null) it.buttons = buttons
})

