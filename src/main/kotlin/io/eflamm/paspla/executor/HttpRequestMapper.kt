package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionData
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInput
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionOutput
import io.eflamm.paspla.model.action.httprequest.HttpRequestConfig
import io.eflamm.paspla.model.action.sendmail.SendMailOutput

var httpRequestMap : (config: HttpRequestConfig, data: ActionData) -> HttpRequestActionInput = {
    config, output ->
        var createdInput : HttpRequestActionInput
        when(output) {
            is HttpRequestActionOutput -> {
                // TODO how am it supposed to know to do the mapping
                createdInput = HttpRequestActionInput(url = config.url, httpVerb = config.httpVerb, queryParams = config.queryParams, headers = config.headers, body = config.body)
            }
            is SendMailOutput -> {
                // TODO
                createdInput = HttpRequestActionInput(url = config.url, httpVerb = config.httpVerb, queryParams = config.queryParams, headers = config.headers, body = config.body)
            }
            else -> {
                createdInput = HttpRequestActionInput(url = config.url, httpVerb = config.httpVerb, queryParams = config.queryParams, headers = config.headers, body = config.body)
            }
        }
        createdInput
}
