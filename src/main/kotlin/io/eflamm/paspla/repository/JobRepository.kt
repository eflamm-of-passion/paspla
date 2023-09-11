package io.eflamm.paspla.repository

import io.eflamm.paspla.model.job.JobEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface JobRepository : CrudRepository<JobEntity, Long> {
    fun findByUuid(uuid: UUID): JobEntity?
}