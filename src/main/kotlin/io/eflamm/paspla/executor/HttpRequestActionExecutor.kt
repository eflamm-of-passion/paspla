package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInput
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class HttpRequestActionExecutor : ActionExecutor<HttpRequestActionInput, String> {

    // HttpResponseOutput
    override fun process(input: HttpRequestActionInput): String {
        val response = RestTemplate().getForEntity(input.url, String::class.java)
        println("${response.statusCode} - ${response.body}")

//        return HttpResponseOutput(url = data.url, code = response.statusCode, body = response.body ?: "")
        return response.body ?: ""
    }

}