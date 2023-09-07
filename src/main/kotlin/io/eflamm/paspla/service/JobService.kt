package io.eflamm.paspla.service

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.JobEntity
import io.eflamm.paspla.model.JobInsertDTO
import io.eflamm.paspla.repository.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class JobService {

    @Autowired
    private lateinit var jobRepository: JobRepository

    fun processJobs(){
//        println("yep it works")
        // TODO get from database, the list of rules to execute
        // TODO process the list of rules
    }


    fun getAllJobs(): List<JobEntity> {
        return jobRepository.findAll().toList()
    }

    fun createJob(jobInsertDTO: JobInsertDTO): JobEntity {
        var jobEntity = JobEntity(
            name = jobInsertDTO.name,
            description = jobInsertDTO.description
        )
        return jobRepository.save(jobEntity)
    }

    fun deleteJob(uuid: UUID) {
        var jobToDelete: JobEntity = jobRepository.findByUuid(uuid) ?: throw ResourceNotFoundException("The rule was not found for the uuid $uuid")
        jobRepository.delete(jobToDelete)
    }
    
}