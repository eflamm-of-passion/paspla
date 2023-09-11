package io.eflamm.paspla.model.action.httprequest

import org.springframework.http.HttpStatusCode

data class HttpResponseOutput(
    var url: String,
    var code: HttpStatusCode,
    var body: String
)
