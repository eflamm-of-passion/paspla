package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionConfig
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionEntity
import io.eflamm.paspla.model.action.sendmail.SendMailActionEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ActionProcessor(var availableActionExecutor: List<ActionExecutor<*,*>>) {

    // TODO i will do some sort of mapping

    @Autowired
    private lateinit var httpRequestActionExecutor: HttpRequestActionExecutor
    @Autowired
    private lateinit var sendMailActionExecutor: SendMailActionExecutor

    fun processActions(actions: List<ActionConfig>) {
        val pairs = actions.asSequence()
            .windowed(size = 2, step = 1, partialWindows = false)
            .toList()

        for ((currentAction, nextAction) in pairs) {
            // TODO get output after exec
            when (currentAction) {
                is HttpRequestActionEntity -> {
                    httpRequestActionExecutor.process(currentAction)
                }
                is SendMailActionEntity -> {
                    sendMailActionExecutor.process(currentAction)
                }
                else -> {
                    // TODO throw exception
                }
            }
            when (nextAction) {
                is HttpRequestActionEntity -> {
                    httpRequestActionExecutor.process(nextAction)
                }
                is SendMailActionEntity -> {
                    sendMailActionExecutor.process(nextAction)
                }
                else -> {
                    // TODO throw exception
                }
            }
        }
    }


    fun getRightExecutor(actionData: HttpRequestActionEntity) {
        return
    }

}