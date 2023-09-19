package io.eflamm.paspla.model.action.httprequest

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "http_request_headers")
data class HttpRequestHeaderEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", insertable = false, updatable = false)
    val uuid: UUID? = null,
    @Column(name="header_key")
    val key: String,
    @Column(name="header_value")
    val value: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "http_request_action_id", referencedColumnName = "id")
    var action: HttpRequestEntity?
) {
    constructor() : this(key = "", value = "", action = null)
}