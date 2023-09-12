package io.eflamm.paspla.model.action.httprequest

data class HttpRequestActionInput(
    val url: String,
    val httpVerb: String,
    val queryParams: String?,
    val headers: String?,
    val body: String?,
) {
    constructor(config: HttpRequestActionConfigEntity) : this(
        config.url,
        config.httpVerb,
        config.queryParams,
        config.headers,
        config.body,
    )

}
