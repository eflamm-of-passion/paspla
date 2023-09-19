package io.eflamm.paspla.model.action.httprequest

enum class HttpRequestHeaderType(val typeName: String) {
    ACCEPT("Accept"),
    CONTENT_TYPE("Content-Type"),
    AUTHORIZATION("Authorization"),
    USER_AGENT("User-Agent"),
    COOKIE("Cookie")
}