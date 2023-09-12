package io.eflamm.paspla.repository

import io.eflamm.paspla.model.action.httprequest.HttpRequestConfig
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HttpRequestActionRepository: CrudRepository<HttpRequestConfig, Long> {

    fun findByUuid(uuid: UUID): HttpRequestConfig?

}