package io.eflamm.paspla.service.action

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.action.sendmail.SendMailConfig
import io.eflamm.paspla.model.action.sendmail.SendMailActionInsertDTO
import io.eflamm.paspla.repository.SendMailActionRepository
import io.eflamm.paspla.service.JobService
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
    private lateinit var jobService: JobService

    fun getAllActions(): List<SendMailConfig> {
        return sendMailActionRepository.findAll().toList()
    }

    fun createAction(actionToCreateDTO: SendMailActionInsertDTO): SendMailConfig {
        var parentJob = jobService.getJobByUuid(actionToCreateDTO.jobUuid) ?: throw ResourceNotFoundException("Could not insert action, the job was not found for the uuid $actionToCreateDTO.jobUuid")
        val actionToCreate = SendMailConfig(
            rank = actionToCreateDTO.rank,
            sender = actionToCreateDTO.sender,
            recipients = actionToCreateDTO.recipients,
            carbonCopyRecipients = actionToCreateDTO.carbonCopyRecipients,
            invisibleCarbonCopyRecipients = actionToCreateDTO.invisibleCarbonCopyRecipients,
            attachmentFilename = actionToCreateDTO.attachmentFilename,
            job = parentJob
        )

        return sendMailActionRepository.save(actionToCreate)
    }

    fun deleteAction(actionToDeleteUuid: UUID) {
        val action = sendMailActionRepository.findByUuid(actionToDeleteUuid) ?: throw ResourceNotFoundException("Could not delete the job, no job was not found for the uuid $actionToDeleteUuid")
        sendMailActionRepository.delete(action)
    }

}