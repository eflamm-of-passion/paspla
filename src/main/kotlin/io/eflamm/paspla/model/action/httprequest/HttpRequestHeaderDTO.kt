package io.eflamm.paspla.model.action.httprequest

import java.util.*

data class HttpRequestHeaderDTO(
    val uuid: UUID?,
    val key: String,
    val value: String,
)
