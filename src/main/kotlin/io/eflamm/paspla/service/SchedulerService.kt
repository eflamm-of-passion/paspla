package io.eflamm.paspla.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SchedulerService {

    @Autowired
    private lateinit var jobsService: JobService

    @Scheduled(fixedDelay = 10000)
    fun triggerAtInterval() {
        jobsService.processJobs()
    }
}

