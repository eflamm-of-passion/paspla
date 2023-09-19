package io.eflamm.paspla.model.action.httprequest

import java.util.*

data class HttpRequestActionInsertDTO(
    val rank: Int,
    val url: String,
    val httpVerb: String,
    val queryParams: String?,
    val headers: List<HttpRequestHeaderInsertDTO>,
    val body: String?,
    val workflowUuid: UUID
)
