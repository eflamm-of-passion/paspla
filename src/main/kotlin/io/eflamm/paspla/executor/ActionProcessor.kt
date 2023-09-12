package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionConfig
import io.eflamm.paspla.model.action.ActionOutput
import io.eflamm.paspla.model.action.InputMapper
import io.eflamm.paspla.model.action.httprequest.HttpRequestConfig
import io.eflamm.paspla.model.action.sendmail.SendMailConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ActionProcessor {

    @Autowired
    private lateinit var httpRequestActionExecutor: HttpRequestActionExecutor
    @Autowired
    private lateinit var sendMailActionExecutor: SendMailActionExecutor

    fun processActions(actions: List<ActionConfig>) {
        val emptyFinalAction = object : ActionConfig {}// the final action will not be processed
        val mutableActions = actions.toMutableList()
        mutableActions.add(emptyFinalAction)
        val actionsByPairs =  mutableActions
//            .sortedBy { it.rank }
            .asSequence()
            .windowed(size = 2, step = 1, partialWindows = false)
            .toList()

        var previousOutputData : ActionOutput? = null
        for ((currentActionConfig, nextActionConfig) in actionsByPairs) {
            // TODO get output after exec
            when (currentActionConfig) {
                is HttpRequestConfig -> {
                    val input = InputMapper.mapInput(currentActionConfig, previousOutputData)
                    if(input != null) {
                        previousOutputData = httpRequestActionExecutor.process(input)
                    }
                }
                is SendMailConfig -> {
                    val input = InputMapper.mapInput(currentActionConfig, previousOutputData)
                    if(input != null) {
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