package io.eflamm.paspla.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SchedulerService {

    @Autowired
    private lateinit var rulesService: JobService

    @Scheduled(fixedDelay = 1000)
    fun triggerAtInterval() {
        rulesService.processJobs()
    }
}

