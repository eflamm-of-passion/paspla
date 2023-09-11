package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.httprequest.HttpRequestActionEntity
import io.eflamm.paspla.model.action.httprequest.HttpResponseOutput
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class HttpRequestActionExecutor : ActionExecutor<HttpRequestActionEntity, HttpResponseOutput> {
    override fun process(data: HttpRequestActionEntity): HttpResponseOutput {
        val response = RestTemplate().getForEntity(data.url, String::class.java)
        println("${response.statusCode} - ${response.body}")

        return HttpResponseOutput(url = data.url, code = response.statusCode, body = response.body ?: "")
    }

}