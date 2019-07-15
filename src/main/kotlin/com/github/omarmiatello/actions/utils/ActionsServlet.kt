package com.github.omarmiatello.actions.utils

import com.github.omarmiatello.actions.MyActionsApp
import org.slf4j.LoggerFactory
import java.util.concurrent.ExecutionException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Handles request received via HTTP POST and delegates it to your Actions app. See: [Request
 * handling in Google App
 * Engine](https://cloud.google.com/appengine/docs/standard/java/how-requests-are-handled).
 */
@WebServlet(name = "actions", value = ["/"])
class ActionsServlet : HttpServlet() {
    private val actionsApp = MyActionsApp()

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/plain"
        response.write("ActionsServlet is listening but requires valid POST request to respond with Action response.")
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        val body = req.bodyString
        LOG.info("doPost, body = {}", body.length)

        try {
            val jsonResponse = actionsApp.handleRequest(body, req.headersMap).get()
            LOG.info("Generated json = {}", jsonResponse)
            res.contentType = "application/json"
            res.write(jsonResponse)
        } catch (e: InterruptedException) {
            handleError(res, e)
        } catch (e: ExecutionException) {
            handleError(res, e)
        }
    }

    private fun handleError(res: HttpServletResponse, throwable: Throwable) {
        LOG.error("Error in App.handleRequest ", throwable)
        res.write("Error handling the intent - " + throwable.message)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(MyActionsApp::class.java)
    }
}
