package io.eflamm.paspla.service

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.executor.ActionProcessor
import io.eflamm.paspla.model.action.ActionConfig
import io.eflamm.paspla.model.action.httprequest.HttpRequestEntity
import io.eflamm.paspla.model.action.sendmail.SendMailEntity
import io.eflamm.paspla.model.workflow.WorkflowEntity
import io.eflamm.paspla.model.workflow.WorkflowInsertDTO
import io.eflamm.paspla.repository.WorkflowRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class WorkflowService {

    @Autowired
    private lateinit var workflowRepository: WorkflowRepository
    @Autowired
    private lateinit var actionProcessor: ActionProcessor

    fun processWorkflows(){
        var workflows = getAllWorkflows().filter { it.isEnabled }.toList()
        workflows.forEach { workflow -> processWorkflow(workflow) }
    }

    fun processWorkflow(workflowEntity: WorkflowEntity) {
        var httpActions: List<HttpRequestEntity> = workflowEntity.httpRequestActions
        var mailActions: List<SendMailEntity> = workflowEntity.sendMailActions

        var actions : List<ActionConfig> = httpActions.plus(mailActions)

        actionProcessor.processActions(actions)
    }


    fun getAllWorkflows(): List<WorkflowEntity> {
        return workflowRepository.findAll().toList()
    }

    fun getWorkflowByUuid(uuid: UUID): WorkflowEntity? {
        return workflowRepository.findByUuid(uuid)
    }

    fun createWorkflow(workflowInsertDTO: WorkflowInsertDTO): WorkflowEntity {
        var workflowEntity = WorkflowEntity(
            name = workflowInsertDTO.name,
            description = workflowInsertDTO.description
        )
        return workflowRepository.save(workflowEntity)
    }

    fun deleteWorkflow(workflowToDeleteUuid: UUID) {
        var workflowToDelete: WorkflowEntity = workflowRepository.findByUuid(workflowToDeleteUuid) ?: throw ResourceNotFoundException("Could not delete the workflow, no workflow was not found for the uuid $workflowToDeleteUuid")
        workflowRepository.delete(workflowToDelete)
    }
    
}