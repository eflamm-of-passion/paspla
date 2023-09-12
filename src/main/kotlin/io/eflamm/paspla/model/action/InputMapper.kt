package io.eflamm.paspla.model.action

import io.eflamm.paspla.model.action.httprequest.HttpRequestActionInput
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionOutput
import io.eflamm.paspla.model.action.httprequest.HttpRequestConfig
import io.eflamm.paspla.model.action.sendmail.SendMailConfig
import io.eflamm.paspla.model.action.sendmail.SendMailInput
import io.eflamm.paspla.model.action.sendmail.SendMailInputAdapter
import io.eflamm.paspla.model.action.sendmail.SendMailOutput

class InputMapper {

    companion object {
        fun mapInput(config: HttpRequestConfig, previousActionOutput: ActionOutput?): HttpRequestActionInput? {
            return HttpRequestActionInput(config)
        }

        fun mapInput(config: SendMailConfig, previousActionOutput: ActionOutput?): SendMailInput? {
            var returnedInput: SendMailInput? = null
            when(previousActionOutput) {
                is HttpRequestActionOutput -> {
                    returnedInput = SendMailInputAdapter(config, previousActionOutput)
                }
                is SendMailOutput -> {
                    // TODO
                }
                else -> {
                    // TODO
                }
            }
            return returnedInput
        }
    }

}