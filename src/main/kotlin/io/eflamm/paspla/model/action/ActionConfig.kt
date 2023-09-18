package io.eflamm.paspla.model.action

import jakarta.persistence.*
import java.util.*

@MappedSuperclass
open class ActionConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", insertable = false, updatable = false)
    open val uuid: UUID? = null,
    open var rank: Int,
) {
    constructor(rank: Int) : this(null, null, rank)
    constructor() : this(null, null, 0)
}