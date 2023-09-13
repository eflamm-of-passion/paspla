package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionConfig
import io.eflamm.paspla.model.action.ActionOutput
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInputAdapter
import io.eflamm.paspla.model.action.httprequest.HttpRequestConfig
import io.eflamm.paspla.model.action.sendmail.SendMailConfig
import io.eflamm.paspla.model.action.sendmail.SendMailInputAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ActionProcessor {

    @Autowired
    private lateinit var httpRequestActionExecutor: HttpRequestActionExecutor

    @Autowired
    private lateinit var sendMailActionExecutor: SendMailActionExecutor

    fun processActions(actions: List<ActionConfig>) {
        val emptyFinalAction = ActionConfig(actions.size)// the final action will not be processed
        val mutableActions = actions.toMutableList()
        mutableActions.add(emptyFinalAction)
        val actionsToExecute = mutableActions
            .sortedBy { it.rank }
            .toList()

        var previousOutputData: ActionOutput? = null
        for (currentActionConfig in actionsToExecute) {
            when (currentActionConfig) {
                is HttpRequestConfig -> {
                    val input = HttpRequestActionInputAdapter.createSendMailInput(currentActionConfig, previousOutputData)
                    if (input != null) {
                        previousOutputData = httpRequestActionExecutor.process(input)
                    }
                }
                is SendMailConfig -> {
                    val input = SendMailInputAdapter.createSendMailInput(currentActionConfig, previousOutputData)
                    if (input != null) {
                        previousOutputData = sendMailActionExecutor.process(input)
                    }
                }
                else -> {
                    // TODO throw exception
                }
            }
        }
    }

}