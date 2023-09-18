package io.eflamm.paspla.controller

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.workflow.WorkflowDTO
import io.eflamm.paspla.model.workflow.WorkflowEntity
import io.eflamm.paspla.model.workflow.WorkflowInsertDTO
import io.eflamm.paspla.service.WorkflowService
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
@RequestMapping("workflows")
@OpenAPIDefinition(
    info= Info(
        title = "Handle the workflows",
        version = "v1"
    )
)
class WorkflowController {

    @Autowired
    private lateinit var workflowService: WorkflowService
    private val logger: Logger = LoggerFactory.getLogger(WorkflowController::class.java)

    @GetMapping("/", produces = ["application/json"])
    fun getWorkflows(): ResponseEntity<List<WorkflowDTO>> {
        logger.info("GET /api/workflows - request")
        var workflowDTOs = mapToDto(workflowService.getAllWorkflows())
        logger.info("GET /api/workflows - response 200 OK ")
        return ResponseEntity.status(HttpStatus.OK).body(workflowDTOs)
    }

    @PostMapping("/", consumes = ["application/json"], produces = ["application/json"])
    fun createWorkflow(@RequestBody workflowInsertDTO: WorkflowInsertDTO): ResponseEntity<WorkflowDTO> {
        logger.info("POST /api/workflows - request")
        var createWorkflowDTO =  mapToDto(workflowService.createWorkflow(workflowInsertDTO))
        logger.info("POST /api/workflows - response 200 OK")
        return ResponseEntity.status(HttpStatus.CREATED).body(createWorkflowDTO)
    }

    @DeleteMapping("/{uuid}")
    fun deleteWorkflow(@PathVariable uuid: UUID): ResponseEntity<Unit> {
        logger.info("DELETE /api/workflows/$uuid")
        try {
            workflowService.deleteWorkflow(uuid)
            logger.info("DELETE /api/workflows/$uuid\" - response 200 OK")
            return ResponseEntity.status(HttpStatus.OK).build()
        } catch (resourceNotFoundException: ResourceNotFoundException) {
            logger.info("DELETE /api/workflows/$uuid\" - response 404 not found")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    private fun mapToDto(workflowEntity: WorkflowEntity): WorkflowDTO {
        return WorkflowDTO(uuid = workflowEntity.uuid, name = workflowEntity.name, description = workflowEntity.description)
    }

    private fun mapToDto(workflowEntities: List<WorkflowEntity>): List<WorkflowDTO> {
        return workflowEntities.map { workflowEntity -> mapToDto(workflowEntity) }
    }
}