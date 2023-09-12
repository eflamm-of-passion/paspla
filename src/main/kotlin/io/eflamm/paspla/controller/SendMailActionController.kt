package io.eflamm.paspla.controller

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.*
import io.eflamm.paspla.model.action.sendmail.SendMailActionDTO
import io.eflamm.paspla.model.action.sendmail.SendMailActionConfigEntity
import io.eflamm.paspla.model.action.sendmail.SendMailActionInsertDTO
import io.eflamm.paspla.service.action.SendMailActionService
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
@RequestMapping("actions/send-mail")
@OpenAPIDefinition(
    info= Info(
        title = "Handle the actions",
        version = "v1"
    )
)
class SendMailActionController {

    @Autowired
    private lateinit var sendMailActionService: SendMailActionService
    private val logger: Logger = LoggerFactory.getLogger(SendMailActionController::class.java)

    @GetMapping("/", produces = ["application/json"])
    fun getHttpRequestActions(): ResponseEntity<List<SendMailActionDTO>> {
        logger.info("GET /api/actions/send-mail/ - request")
        // TODO
        logger.info("GET /api/actions/send-mail/ - response 200 OK ")
        return ResponseEntity.status(HttpStatus.OK).body(emptyList())
    }

    @PostMapping("/", consumes = ["application/json"], produces = ["application/json"])
    fun createAction(@RequestBody insertDTO: SendMailActionInsertDTO): ResponseEntity<SendMailActionDTO> {
        logger.info("POST /api/actions/send-mail/ - request")
        var createActionDTO =  mapToDto(sendMailActionService.createAction(insertDTO))
        logger.info("POST /api/actions/send-mail/ - response 200 OK")
        return ResponseEntity.status(HttpStatus.CREATED).body(createActionDTO)
    }

    @DeleteMapping("/{uuid}")
    fun deleteAction(@PathVariable uuid: UUID): ResponseEntity<Unit> {
        logger.info("DELETE /api/actions/http-request//$uuid")
        try {
            sendMailActionService.deleteAction(uuid)
            logger.info("DELETE /api/actions/send-mail/$uuid\" - response 200 OK")
            return ResponseEntity.status(HttpStatus.OK).build()
        } catch (resourceNotFoundException: ResourceNotFoundException) {
            logger.info("DELETE /api/actions/send-mail/$uuid\" - response 404 not found")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    private fun mapToDto(entity: SendMailActionConfigEntity): SendMailActionDTO {
        return SendMailActionDTO(
            uuid = entity.uuid,
            rank = entity.rank,
            sender = entity.sender,
            recipients = entity.recipients,
            carbonCopyRecipients = entity.carbonCopyRecipients,
            invisibleCarbonCopyRecipients = entity.invisibleCarbonCopyRecipients,
            attachmentFilename = entity.attachmentFilename,
            jobUuid = entity.job?.uuid
        )
    }

    private fun mapToDto(entities: List<SendMailActionConfigEntity>): List<SendMailActionDTO> {
        return entities.map { entity -> mapToDto(entity) }
    }
}