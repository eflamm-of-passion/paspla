package io.eflamm.paspla.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="rules")
data class JobEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", insertable = false, updatable = false)
    val uuid: UUID? = null,
    val name: String,
    val description: String
) {
    constructor() : this(null, null, "", "")
}