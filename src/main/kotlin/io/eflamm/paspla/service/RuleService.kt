package io.eflamm.paspla.service

import io.eflamm.paspla.model.RuleEntity
import io.eflamm.paspla.model.RuleInsertDTO
import io.eflamm.paspla.repository.RuleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RuleService {

    @Autowired
    private lateinit var ruleRepository: RuleRepository

    fun processRules(){
//        println("yep it works")
        // TODO get from database, the list of rules to execute
        // TODO process the list of rules
    }

    fun createRule(ruleInsertDTO: RuleInsertDTO): RuleEntity {
        var ruleEntity: RuleEntity = RuleEntity(
            name = ruleInsertDTO.name,
            description = ruleInsertDTO.description
        )
        return ruleRepository.save(ruleEntity)
    }
    
}