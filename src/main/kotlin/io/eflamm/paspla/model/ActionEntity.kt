package io.eflamm.paspla.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="actions")
data class ActionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", insertable = false, updatable = false)
    val uuid: UUID?,
    val rank: Int,
    val type: String,
    val value: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    val rule: JobEntity
)