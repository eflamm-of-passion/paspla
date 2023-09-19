package io.eflamm.paspla.service.action

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInsertDTO
import io.eflamm.paspla.model.action.httprequest.HttpRequestEntity
import io.eflamm.paspla.model.action.httprequest.HttpRequestHeaderEntity
import io.eflamm.paspla.repository.HttpRequestActionRepository
import io.eflamm.paspla.repository.HttpRequestHeaderRepository
import io.eflamm.paspla.service.WorkflowService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class HttpRequestActionService() : ActionService {

    @Autowired
    private lateinit var httpRequestActionRepository: HttpRequestActionRepository
    @Autowired
    private lateinit var httpRequestHeaderRepository: HttpRequestHeaderRepository
    @Autowired
    private lateinit var workflowService: WorkflowService

    fun getAllActions(): List<HttpRequestEntity> {
        return httpRequestActionRepository.findAll().toList()
    }

    fun createAction(actionToCreateDTO: HttpRequestActionInsertDTO): HttpRequestEntity {
        var parentWorkflow = workflowService.getWorkflowByUuid(actionToCreateDTO.workflowUuid) ?: throw ResourceNotFoundException("Could not insert action, the workflow was not found for the uuid $actionToCreateDTO.workflowUuid")
        val actionToCreate = HttpRequestEntity(
            rank = actionToCreateDTO.rank,
            url = actionToCreateDTO.url,
            httpVerb = actionToCreateDTO.httpVerb,
            queryParams = actionToCreateDTO.queryParams,
            body = actionToCreateDTO.body,
            workflow = parentWorkflow
        )
        val createdAction = httpRequestActionRepository.save(actionToCreate)
        val headers = actionToCreateDTO.headers.map{headerDto -> HttpRequestHeaderEntity(key = headerDto.key, value = headerDto.value, action = createdAction) }
        val createdHeaders = headers.map { headerToCreate -> httpRequestHeaderRepository.save(headerToCreate) }.toList()

        return HttpRequestEntity(createdAction, createdHeaders)
    }

    fun updateAction(actionToUpdateUuid: UUID, updatedAction: HttpRequestEntity) {
        TODO("Not yet implemented")
    }

    fun deleteAction(actionToDeleteUuid: UUID) {
        val action = httpRequestActionRepository.findByUuid(actionToDeleteUuid) ?: throw ResourceNotFoundException("Could not delete the workflow, no workflow was not found for the uuid $actionToDeleteUuid")
        httpRequestActionRepository.delete(action)
    }


}