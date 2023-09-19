package io.eflamm.paspla.service.action

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.action.sendmail.SendMailActionInsertDTO
import io.eflamm.paspla.model.action.sendmail.SendMailEntity
import io.eflamm.paspla.repository.SendMailActionRepository
import io.eflamm.paspla.service.WorkflowService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class SendMailActionService : ActionService {

    @Autowired
    private lateinit var sendMailActionRepository: SendMailActionRepository
    @Autowired
    private lateinit var workflowService: WorkflowService

    fun getAllActions(): List<SendMailEntity> {
        return sendMailActionRepository.findAll().toList()
    }

    fun createAction(actionToCreateDTO: SendMailActionInsertDTO): SendMailEntity {
        var parentWorkflow = workflowService.getWorkflowByUuid(actionToCreateDTO.workflowUuid) ?: throw ResourceNotFoundException("Could not insert action, the workflow was not found for the uuid $actionToCreateDTO.workflowUuid")
        val actionToCreate = SendMailEntity(
            rank = actionToCreateDTO.rank,
            sender = actionToCreateDTO.sender,
            recipients = actionToCreateDTO.recipients,
            carbonCopyRecipients = actionToCreateDTO.carbonCopyRecipients,
            invisibleCarbonCopyRecipients = actionToCreateDTO.invisibleCarbonCopyRecipients,
            attachmentFilename = actionToCreateDTO.attachmentFilename,
            body = actionToCreateDTO.body,
            workflow = parentWorkflow
        )

        return sendMailActionRepository.save(actionToCreate)
    }

    fun deleteAction(actionToDeleteUuid: UUID) {
        val action = sendMailActionRepository.findByUuid(actionToDeleteUuid) ?: throw ResourceNotFoundException("Could not delete the workflow, no workflow was not found for the uuid $actionToDeleteUuid")
        sendMailActionRepository.delete(action)
    }

}