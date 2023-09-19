package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionData
import io.eflamm.paspla.model.action.httprequest.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

class HttpRequestActionExecutor

var executeHttpRequestAction : (config: HttpRequestEntity, data: ActionData, adapter: (HttpRequestEntity, ActionData) -> HttpRequestActionInput) -> ActionData = {
    config, data, adapter ->

    val logger: Logger = LoggerFactory.getLogger(HttpRequestActionExecutor::class.java)

    val input = adapter(config, data)

    var request = RestTemplate()

    val cookies: HttpRequestHeaderEntity? = input.headers?.firstOrNull { header -> header.key == HttpRequestHeaderType.COOKIE.typeName }
    val httpHeaders = cookies?.let { createHeaders(it.value) } ?: HttpHeaders()

    request = addCookiesToRequest(request, httpHeaders)


    val response = request.getForEntity(input.url, String::class.java)
    logger.info("${response.statusCode} - ${response.body}")

    HttpRequestActionOutput(url = input.url, code = response.statusCode, body = response.body ?: "")
}

private var createHeaders : (cookiesAsString: String) -> HttpHeaders = {
    cookiesAsString ->
    var cookiesAsList = cookiesAsString.replace("\\s".toRegex(), "")?.split(";") ?: emptyList()
    val httpHeaders = HttpHeaders()
    cookiesAsList.forEach { cookie ->
        httpHeaders.add(HttpHeaders.COOKIE, cookie)
    }
    httpHeaders
}

private val addCookiesToRequest : (request: RestTemplate, cookies: HttpHeaders) -> RestTemplate = {
    request, cookies ->
    val requestFactory = SimpleClientHttpRequestFactory()
    request.requestFactory = requestFactory
    val interceptors = request.interceptors.toMutableList()
    interceptors.add(ClientHttpRequestInterceptor { request, body, execution ->
        request.headers.addAll(cookies)
        execution.execute(request, body)
    })

    request.interceptors = interceptors
    request
}