package io.eflamm.paspla.service.action

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionEntity
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInsertDTO
import io.eflamm.paspla.repository.HttpRequestActionRepository
import io.eflamm.paspla.service.JobService
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
    private lateinit var jobService: JobService

    fun createAction(actionToCreateDTO: HttpRequestActionInsertDTO): HttpRequestActionEntity {
        var parentJob = jobService.getJobByUuid(actionToCreateDTO.jobUuid) ?: throw ResourceNotFoundException("Could not insert action, the job was not found for the uuid $actionToCreateDTO.jobUuid")
        val actionToCreate = HttpRequestActionEntity(
            rank = actionToCreateDTO.rank,
            url = actionToCreateDTO.url,
            httpVerb = actionToCreateDTO.httpVerb,
            queryParams = actionToCreateDTO.queryParams,
            headers = actionToCreateDTO.headers,
            body = actionToCreateDTO.body,
            job = parentJob
        )
        actionToCreate.job = parentJob

        return httpRequestActionRepository.save(actionToCreate)
    }

    fun updateAction(actionToUpdateUuid: UUID, updatedAction: HttpRequestActionEntity) {
        TODO("Not yet implemented")
    }

    fun deleteAction(actionToDeleteUuid: UUID) {
        val action = httpRequestActionRepository.findByUuid(actionToDeleteUuid) ?: throw ResourceNotFoundException("Could not delete the job, no job was not found for the uuid $actionToDeleteUuid")
        httpRequestActionRepository.delete(action)
    }


}