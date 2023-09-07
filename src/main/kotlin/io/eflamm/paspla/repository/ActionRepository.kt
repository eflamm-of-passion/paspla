package io.eflamm.paspla.repository

import io.eflamm.paspla.model.ActionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActionRepository: JpaRepository<ActionEntity, Long> {}