package com.github.omarmiatello.actions

import com.google.actions.api.test.MockRequestBuilder
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class MyActionsAppTest {

    @Test
    fun test_welcome_using_raw_request() {
        val app = MyActionsApp()
        val requestBody = readText("request_welcome.json")
        val expectedResponse = readText("response_welcome.json")

        val future = app.handleRequest(requestBody, headers = null)

        val responseJson = future.get()
        assertEquals(expectedResponse, responseJson)
    }

    @Test
    fun test_welcome_using_mock_request_builder() {
        val app = MyActionsApp()
        val rb = MockRequestBuilder.welcome("welcome", usesDialogflow = true)
        val request = rb.build()

        val response = app.welcome(request)
        assertTrue(response.expectUserResponse!!)
        assertEquals(1, response.richResponse!!.items.size)
    }

    @Test
    fun test_bye() {
        val app = MyActionsApp()
        val rb = MockRequestBuilder()
        rb.setIntent("bye")
        rb.setUsesDialogflow(true)

        val request = rb.build()
        val response = app.bye(request)

        assertFalse(response.expectUserResponse!!)
        assertEquals(1, response.richResponse!!.items.size)
    }
}
