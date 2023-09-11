package io.eflamm.paspla.repository

import io.eflamm.paspla.model.SendMailActionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SendMailActionRepository: CrudRepository<SendMailActionEntity, Long> {
    fun findByUuid(uuid: UUID): SendMailActionEntity?

}