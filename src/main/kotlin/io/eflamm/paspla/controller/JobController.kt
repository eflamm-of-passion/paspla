package io.eflamm.paspla.controller

import io.eflamm.paspla.exception.ResourceNotFoundException
import io.eflamm.paspla.model.job.JobDTO
import io.eflamm.paspla.model.job.JobEntity
import io.eflamm.paspla.model.job.JobInsertDTO
import io.eflamm.paspla.service.JobService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.util.*

@RestController
@RequestMapping("jobs")
@OpenAPIDefinition(
    info= Info(
        title = "Handle the jobs",
        version = "v1"
    )
)
class JobController {

    @Autowired
    private lateinit var jobsService: JobService
    private val logger: Logger = LoggerFactory.getLogger(JobController::class.java)

    @GetMapping("/test", produces = ["application/json"])
    fun test(): ResponseEntity<String> {
        val response = RestTemplate().getForObject("https://pokeapi.co/api/v2/pokemon/ditto", String::class.java)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/", produces = ["application/json"])
    fun getJobs(): ResponseEntity<List<JobDTO>> {
        logger.info("GET /api/jobs - request")
        var jobDTOs = mapToDto(jobsService.getAllJobs())
        logger.info("GET /api/jobs - response 200 OK ")
        return ResponseEntity.status(HttpStatus.OK).body(jobDTOs)
    }

    @PostMapping("/", consumes = ["application/json"], produces = ["application/json"])
    fun createJob(@RequestBody jobInsertDTO: JobInsertDTO): ResponseEntity<JobDTO> {
        logger.info("POST /api/jobs - request")
        var createJobDTO =  mapToDto(jobsService.createJob(jobInsertDTO))
        logger.info("POST /api/jobs - response 200 OK")
        return ResponseEntity.status(HttpStatus.CREATED).body(createJobDTO)
    }

    @DeleteMapping("/{uuid}")
    fun deleteJob(@PathVariable uuid: UUID): ResponseEntity<Unit> {
        logger.info("DELETE /api/jobs/$uuid")
        try {
            jobsService.deleteJob(uuid)
            logger.info("DELETE /api/jobs/$uuid\" - response 200 OK")
            return ResponseEntity.status(HttpStatus.OK).build()
        } catch (resourceNotFoundException: ResourceNotFoundException) {
            logger.info("DELETE /api/jobs/$uuid\" - response 404 not found")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    private fun mapToDto(jobEntity: JobEntity): JobDTO {
        return JobDTO(uuid = jobEntity.uuid, name = jobEntity.name, description = jobEntity.description)
    }

    private fun mapToDto(jobEntities: List<JobEntity>): List<JobDTO> {
        return jobEntities.map { jobEntity -> mapToDto(jobEntity) }
    }
}