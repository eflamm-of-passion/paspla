package io.eflamm.paspla.controller

import io.eflamm.paspla.model.RuleInsertDTO
import io.eflamm.paspla.service.RuleService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("rules")
@OpenAPIDefinition(
    info= Info(
        title = "Handle the API rules",
        version = "v1"
    )
)
class RuleController {

    @Autowired
    private lateinit var rulesService: RuleService

    @GetMapping("/", produces = ["application/json"])
    fun getRules(): ResponseEntity<String> {
        val response = RestTemplate().getForObject("https://pokeapi.co/api/v2/pokemon/ditto", String::class.java)
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response)
    }

    @PostMapping("/", consumes = ["application/json"])
    fun createRule(@RequestBody ruleInsertDTO: RuleInsertDTO) {
        rulesService.createRule(ruleInsertDTO)
    }
}