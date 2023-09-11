package io.eflamm.paspla.model

import io.eflamm.paspla.service.actions.Action
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "http_request_actions")
data class HttpRequestActionEntity(
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
) : Action<Unit, HttpResponseOutput> {

    constructor() : this(id = null, uuid = null, rank = 0, url = "", httpVerb = "", queryParams = null, headers= null, body= null, job = null)

    override fun process(data: Unit?): HttpResponseOutput {
        TODO("Not yet implemented")
        println("this is an http request")
    }
}
