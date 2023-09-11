package io.eflamm.paspla.controller

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.*
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionDTO
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionEntity
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInsertDTO
import io.eflamm.paspla.service.action.HttpRequestActionService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("actions/http-request")
@OpenAPIDefinition(
    info= Info(
        title = "Handle the actions",
        version = "v1"
    )
)
class HttpRequestActionController {

    @Autowired
    private lateinit var httpRequestActionService: HttpRequestActionService
    private val logger: Logger = LoggerFactory.getLogger(HttpRequestActionController::class.java)

    @GetMapping("/", produces = ["application/json"])
    fun getHttpRequestActions(): ResponseEntity<List<HttpRequestActionInsertDTO>> {
        logger.info("GET /api/actions/http-request/ - request")
        // TODO
        logger.info("GET /api/actions/http-request/ - response 200 OK ")
        return ResponseEntity.status(HttpStatus.OK).body(emptyList())
    }

    @PostMapping("/", consumes = ["application/json"], produces = ["application/json"])
    fun createAction(@RequestBody insertDTO: HttpRequestActionInsertDTO): ResponseEntity<HttpRequestActionDTO> {
        logger.info("POST /api/actions/http-request/ - request")
        var createActionDTO =  mapToDto(httpRequestActionService.createAction(insertDTO))
        logger.info("POST /api/actions/http-request/ - response 200 OK")
        return ResponseEntity.status(HttpStatus.CREATED).body(createActionDTO)
    }

    @DeleteMapping("/{uuid}")
    fun deleteAction(@PathVariable uuid: UUID): ResponseEntity<Unit> {
        logger.info("DELETE /api/actions/http-request//$uuid")
        try {
            httpRequestActionService.deleteAction(uuid)
            logger.info("DELETE /api/actions/http-request/$uuid\" - response 200 OK")
            return ResponseEntity.status(HttpStatus.OK).build()
        } catch (resourceNotFoundException: ResourceNotFoundException) {
            logger.info("DELETE /api/actions/http-request/jobs/$uuid\" - response 404 not found")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    private fun mapToDto(entity: HttpRequestActionEntity): HttpRequestActionDTO {
        return HttpRequestActionDTO(
            uuid = entity.uuid,
            rank = entity.rank,
            url = entity.url,
            httpVerb = entity.httpVerb,
            queryParams = entity.queryParams,
            headers = entity.headers,
            body = entity.body,
            jobUuid = entity.job?.uuid
        )
    }

    private fun mapToDto(entities: List<HttpRequestActionEntity>): List<HttpRequestActionDTO> {
        return entities.map { entity -> mapToDto(entity) }
    }
}