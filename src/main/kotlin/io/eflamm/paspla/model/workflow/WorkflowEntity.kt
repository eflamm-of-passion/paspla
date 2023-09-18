package io.eflamm.paspla.model.workflow

import io.eflamm.paspla.model.action.httprequest.HttpRequestConfig
import io.eflamm.paspla.model.action.sendmail.SendMailConfig
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="workflows")
data class WorkflowEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", insertable = false, updatable = false)
    val uuid: UUID? = null,
    val name: String,
    val description: String,

    @OneToMany(mappedBy = "workflow", cascade = [CascadeType.ALL], orphanRemoval = true)
    val httpRequestActions: List<HttpRequestConfig> = mutableListOf(),
    @OneToMany(mappedBy = "workflow", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sendMailActions: List<SendMailConfig> = mutableListOf()
) {
    constructor() : this(null, null, "", "")
}