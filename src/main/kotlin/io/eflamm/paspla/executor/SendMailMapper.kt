package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionData
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionOutput
import io.eflamm.paspla.model.action.sendmail.SendMailConfig
import io.eflamm.paspla.model.action.sendmail.SendMailInput
import io.eflamm.paspla.model.action.sendmail.SendMailInputAdapter
import io.eflamm.paspla.model.action.sendmail.SendMailOutput

var sendMailInputMap : (config: SendMailConfig, data: ActionData) -> SendMailInput = {
    config, data ->
        var createdSendMailInput : SendMailInput
        when(data) {
            is HttpRequestActionOutput -> {
                createdSendMailInput = SendMailInputAdapter(config, data)
            }
            is SendMailOutput -> {
                createdSendMailInput = SendMailInputAdapter(config)
            }
            else -> {
                createdSendMailInput = SendMailInputAdapter(config)
            }
        }
        createdSendMailInput
}