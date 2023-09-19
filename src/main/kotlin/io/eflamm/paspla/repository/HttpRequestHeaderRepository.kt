package io.eflamm.paspla.repository

import io.eflamm.paspla.model.action.httprequest.HttpRequestHeaderEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HttpRequestHeaderRepository: CrudRepository<HttpRequestHeaderEntity, Long>