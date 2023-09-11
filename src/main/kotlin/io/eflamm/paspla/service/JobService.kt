package io.eflamm.paspla.service

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.executor.HttpRequestActionExecutor
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionEntity
import io.eflamm.paspla.model.job.JobEntity
import io.eflamm.paspla.model.job.JobInsertDTO
import io.eflamm.paspla.repository.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class JobService {

    @Autowired
    private lateinit var jobRepository: JobRepository
    @Autowired
    private lateinit var httpRequestActionExecutor: HttpRequestActionExecutor

    fun processJobs(){
//        println("yep it works")
        // TODO get from database, the list of jobs to execute
        // TODO process the list of jobs
        var jobs = getAllJobs()
        jobs.forEach { job -> processJob(job) }
    }

    fun processJob(jobEntity: JobEntity) {
        // TODO refacto the execution of all actions
        var actions: List<HttpRequestActionEntity> = jobEntity.httpRequestActions
        actions.forEach { action -> httpRequestActionExecutor.process(action) }
    }


    fun getAllJobs(): List<JobEntity> {
        return jobRepository.findAll().toList()
    }

    fun getJobByUuid(uuid: UUID): JobEntity? {
        return jobRepository.findByUuid(uuid)
    }

    fun createJob(jobInsertDTO: JobInsertDTO): JobEntity {
        var jobEntity = JobEntity(
            name = jobInsertDTO.name,
            description = jobInsertDTO.description
        )
        return jobRepository.save(jobEntity)
    }

    fun deleteJob(jobToDeleteUuid: UUID) {
        var jobToDelete: JobEntity = jobRepository.findByUuid(jobToDeleteUuid) ?: throw ResourceNotFoundException("Could not delete the job, no job was not found for the uuid $jobToDeleteUuid")
        jobRepository.delete(jobToDelete)
    }
    
}