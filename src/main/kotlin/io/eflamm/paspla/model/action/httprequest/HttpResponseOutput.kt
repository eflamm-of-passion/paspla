package io.eflamm.paspla.model.action.httprequest

import org.springframework.http.HttpStatusCode

data class HttpResponseOutput(
    var code: HttpStatusCode,
    var body: String
)
