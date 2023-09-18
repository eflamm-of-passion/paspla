package io.eflamm.paspla.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SchedulerService {

    companion object {
        const val fixedDelayInMilis: Long = 300000 // 5mn
    }
    @Autowired
    private lateinit var workflowsService: WorkflowService

    @Scheduled(fixedDelay = fixedDelayInMilis)
    fun triggerAtInterval() {
        workflowsService.processWorkflows()
    }
}

