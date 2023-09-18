package io.eflamm.paspla.repository

import io.eflamm.paspla.model.workflow.WorkflowEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface WorkflowRepository : CrudRepository<WorkflowEntity, Long> {
    fun findByUuid(uuid: UUID): WorkflowEntity?
}