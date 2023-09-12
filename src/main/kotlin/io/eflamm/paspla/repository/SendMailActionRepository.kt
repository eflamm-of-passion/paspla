package io.eflamm.paspla.repository

import io.eflamm.paspla.model.action.sendmail.SendMailConfig
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SendMailActionRepository: CrudRepository<SendMailConfig, Long> {
    fun findByUuid(uuid: UUID): SendMailConfig?

}