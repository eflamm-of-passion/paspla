package io.eflamm.paspla.model.job

import io.eflamm.paspla.model.action.httprequest.HttpRequestActionConfigEntity
import io.eflamm.paspla.model.action.sendmail.SendMailActionConfigEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="jobs")
data class JobEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", insertable = false, updatable = false)
    val uuid: UUID? = null,
    val name: String,
    val description: String,

    @OneToMany(mappedBy = "job", cascade = [CascadeType.ALL], orphanRemoval = true)
    val httpRequestActions: List<HttpRequestActionConfigEntity> = mutableListOf(),
    @OneToMany(mappedBy = "job", cascade = [CascadeType.ALL], orphanRemoval = true)
    val sendMailActions: List<SendMailActionConfigEntity> = mutableListOf()
) {
    constructor() : this(null, null, "", "")
}