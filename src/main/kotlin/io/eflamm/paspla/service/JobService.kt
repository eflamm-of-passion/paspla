package io.eflamm.paspla.service

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.HttpRequestActionEntity
import io.eflamm.paspla.model.JobEntity
import io.eflamm.paspla.model.JobInsertDTO
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

    fun processJobs(){
//        println("yep it works")
        // TODO get from database, the list of jobs to execute
        // TODO process the list of jobs
        var jobs = getAllJobs()
        jobs.forEach { job -> processJob(job) }
    }

    fun processJob(jobEntity: JobEntity) {
        var actions: List<HttpRequestActionEntity> = jobEntity.httpRequestActions
        actions.forEach { action -> println(action.url) }
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