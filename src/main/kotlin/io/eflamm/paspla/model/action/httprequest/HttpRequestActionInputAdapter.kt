package io.eflamm.paspla.model.action.httprequest

import io.eflamm.paspla.model.action.ActionData
import io.eflamm.paspla.model.action.sendmail.SendMailOutput

data class HttpRequestActionInputAdapter(
    override val url: String,
    override val httpVerb: String,
    override val queryParams: String?,
    override val headers: List<HttpRequestHeaderEntity>,
    override val body: String?,
) : HttpRequestActionInput(url, httpVerb, queryParams, headers, body) {

    companion object {
        // design pattern : factory method
        fun createInput(config: HttpRequestEntity, output: ActionData?): HttpRequestActionInput? {
            var createdSendMailInput : HttpRequestActionInput? = null
            when(output) {
                is HttpRequestActionOutput -> {
                    // TODO
                }
                is SendMailOutput -> {
                    // TODO
                }
                else -> {
                    createdSendMailInput = HttpRequestActionInput(url = config.url, httpVerb = config.httpVerb, queryParams = config.queryParams, headers = config.headers, body = config.body)
                }
            }
            return createdSendMailInput
        }
    }
}
