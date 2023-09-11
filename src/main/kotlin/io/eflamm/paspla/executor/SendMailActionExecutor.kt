package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.sendmail.SendMailActionEntity
import org.springframework.stereotype.Component

@Component
class SendMailActionExecutor : ActionExecutor<SendMailActionEntity, String> {
    override fun process(data: SendMailActionEntity): String {
        println("sending mail")

        return ""
    }

}