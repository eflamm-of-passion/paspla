package io.eflamm.paspla.controller

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.*
import io.eflamm.paspla.model.action.httprequest.*
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
        title = "Handle the actions to make an HTTP request.",
        version = "v1"
    )
)
class HttpRequestActionController {

    @Autowired
    private lateinit var httpRequestActionService: HttpRequestActionService
    private val logger: Logger = LoggerFactory.getLogger(HttpRequestActionController::class.java)

    @GetMapping("/", produces = ["application/json"])
    fun getHttpRequestActions(): ResponseEntity<List<HttpRequestActionDTO>> {
        logger.info("GET /api/actions/http-request/ - request")
        var actionsDTOs = mapRequestsToDto(httpRequestActionService.getAllActions())
        logger.info("GET /api/actions/http-request/ - response 200 OK ")
        return ResponseEntity.status(HttpStatus.OK).body(actionsDTOs)
    }

    @PostMapping("/", consumes = ["application/json"], produces = ["application/json"])
    fun createAction(@RequestBody insertDTO: HttpRequestActionInsertDTO): ResponseEntity<HttpRequestActionDTO> {
        logger.info("POST /api/actions/http-request/ - request")
        var createActionDTO =  mapRequestToDto(httpRequestActionService.createAction(insertDTO))
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
            logger.info("DELETE /api/actions/http-request/$uuid\" - response 404 not found")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    private fun mapRequestToDto(requestEntity: HttpRequestEntity): HttpRequestActionDTO {
        return HttpRequestActionDTO(
            uuid = requestEntity.uuid,
            rank = requestEntity.rank,
            url = requestEntity.url,
            httpVerb = requestEntity.httpVerb,
            queryParams = requestEntity.queryParams,
            headers = mapHeadersToDto(requestEntity.headers),
            body = requestEntity.body,
            workflowUuid = requestEntity.workflow?.uuid
        )
    }

    private fun mapRequestsToDto(requestEntities: List<HttpRequestEntity>): List<HttpRequestActionDTO> {
        return requestEntities.map { entity -> mapRequestToDto(entity) }
    }

    private fun mapHeaderToDto(headerEntity: HttpRequestHeaderEntity): HttpRequestHeaderDTO {
        return HttpRequestHeaderDTO(
            uuid = headerEntity.uuid,
            key = headerEntity.key,
            value = headerEntity.value
        )
    }

    private fun mapHeadersToDto(headerEntities: List<HttpRequestHeaderEntity>): List<HttpRequestHeaderDTO> {
        return headerEntities.map { entity -> mapHeaderToDto(entity) }
    }
}