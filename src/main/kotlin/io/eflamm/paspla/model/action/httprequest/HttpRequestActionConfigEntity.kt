package io.eflamm.paspla.model.action.httprequest

import io.eflamm.paspla.model.action.ActionConfig
import io.eflamm.paspla.model.job.JobEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "http_request_actions")
data class HttpRequestActionConfigEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", insertable = false, updatable = false)
    val uuid: UUID? = null,
    val rank: Int,
    val url: String,
    @Column(name = "http_verb")
    val httpVerb: String,
    @Column(name = "query_params")
    val queryParams: String?,
    val headers: String?,
    val body: String?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    var job: JobEntity?
) : ActionConfig {
    constructor() : this(id = null, uuid = null, rank = 0, url = "", httpVerb = "", queryParams = null, headers= null, body= null, job = null)
}