package io.eflamm.paspla.repository

import io.eflamm.paspla.model.RuleEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RuleRepository : CrudRepository<RuleEntity, Long> {}