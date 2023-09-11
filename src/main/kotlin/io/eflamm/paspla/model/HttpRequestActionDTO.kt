package io.eflamm.paspla.model

import java.util.*

data class HttpRequestActionDTO(
    val uuid: UUID?,
    val rank: Int,
    val url: String,
    val httpVerb: String,
    val queryParams: String?,
    val headers: String?,
    val body: String?,
    var jobUuid: UUID?
)
