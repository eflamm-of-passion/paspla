package io.eflamm.paspla.model.action.httprequest

open class HttpRequestActionInput(
    open val url: String,
    open val httpVerb: String,
    open val queryParams: String?,
    open val headers: String?,
    open val body: String?,
)
