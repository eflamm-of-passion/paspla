package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInput
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionOutput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class HttpRequestActionExecutor : ActionExecutor<HttpRequestActionInput, HttpRequestActionOutput> {

    private val logger: Logger = LoggerFactory.getLogger(HttpRequestActionExecutor::class.java)

    override fun process(input: HttpRequestActionInput): HttpRequestActionOutput {
        val response = RestTemplate().getForEntity(input.url, String::class.java)
        logger.info("${response.statusCode} - ${response.body}")

        return HttpRequestActionOutput(url = input.url, code = response.statusCode, body = response.body ?: "")
    }

}