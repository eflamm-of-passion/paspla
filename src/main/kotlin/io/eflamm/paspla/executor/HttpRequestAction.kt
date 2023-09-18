package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionData
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInput
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionOutput
import io.eflamm.paspla.model.action.httprequest.HttpRequestConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.client.RestTemplate

class HttpRequestActionExecutor

var executeHttpRequestAction : (config: HttpRequestConfig, data: ActionData, adapter: (HttpRequestConfig, ActionData) -> HttpRequestActionInput) -> ActionData = {
    config, data, adapter ->

    val logger: Logger = LoggerFactory.getLogger(HttpRequestActionExecutor::class.java)

    val input = adapter(config, data)
    val response = RestTemplate().getForEntity(input.url, String::class.java)
    logger.info("${response.statusCode} - ${response.body}")

    HttpRequestActionOutput(url = input.url, code = response.statusCode, body = response.body ?: "")
}