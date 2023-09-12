package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionConfig
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionConfigEntity
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInput
import io.eflamm.paspla.model.action.sendmail.SendMailActionConfigEntity
import io.eflamm.paspla.model.action.sendmail.SendMailInput
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
        val emptyFinalAction: ActionConfig = object : ActionConfig {} // the final action will not be processed
        val mutableActions = actions.toMutableList()
        mutableActions.add(emptyFinalAction)
        val actionsByPairs =  mutableActions.asSequence()
            .windowed(size = 2, step = 1, partialWindows = false)
            .toList()

        var nextActionInputData : Any? = null
        for ((currentActionConfig, nextActionConfig) in actionsByPairs) {
            // TODO get output after exec
            when (currentActionConfig) {
                is HttpRequestActionConfigEntity -> {
                    val input = HttpRequestActionInput(currentActionConfig)
                    nextActionInputData = httpRequestActionExecutor.process(input)
                }
                is SendMailActionConfigEntity -> {
                    val input = SendMailInput(
                        sender = currentActionConfig.sender,
                        recipients = currentActionConfig.recipients,
                        carbonCopyRecipients = currentActionConfig.carbonCopyRecipients,
                        invisibleCarbonCopyRecipients = currentActionConfig.invisibleCarbonCopyRecipients,
                        attachmentFilename = currentActionConfig.attachmentFilename,
                        body = nextActionInputData.toString()
                    )
                    nextActionInputData = sendMailActionExecutor.process(input)
                }
                else -> {
                    // TODO throw exception
                }
            }
        }
    }


    fun getRightExecutor(actionData: HttpRequestActionConfigEntity) {
        return
    }

}