package io.eflamm.paspla.repository

import io.eflamm.paspla.model.action.httprequest.HttpRequestEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HttpRequestActionRepository: CrudRepository<HttpRequestEntity, Long> {

    fun findByUuid(uuid: UUID): HttpRequestEntity?

}