package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionConfig
import io.eflamm.paspla.model.action.ActionData
import io.eflamm.paspla.model.action.EmptyData
import io.eflamm.paspla.model.action.httprequest.HttpRequestConfig
import io.eflamm.paspla.model.action.sendmail.SendMailConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class ActionProcessor {

    @Autowired
    private lateinit var mailSender: JavaMailSender

    fun processActions(actions: List<ActionConfig>) {
        val emptyFinalAction = ActionConfig(actions.size)// the final action will not be processed
        val mutableActions = actions.toMutableList()
        mutableActions.add(emptyFinalAction)
        // TODO generate the list of actions, insert the adapter
        val actionsToExecute = mutableActions
            .sortedBy { it.rank }
            .toList()

        var previousOutputData: ActionData = EmptyData()
        for (currentActionConfig in actionsToExecute) {
            previousOutputData = processTheAccordingFunction(currentActionConfig, previousOutputData)
        }
    }

    fun processTheAccordingFunction(actionConfig: ActionConfig, previousOutputData: ActionData): ActionData {
        var data = previousOutputData
            when (actionConfig) {
                is HttpRequestConfig -> {
                        data = executeHttpRequestAction(actionConfig, data, httpRequestMap)
                }
                is SendMailConfig -> {
                        data = executeSendMailAction(actionConfig, data, mailSender, sendMailInputMap)
                }
                else -> {
                    // TODO throw exception
                }
            }
        return data
    }

}