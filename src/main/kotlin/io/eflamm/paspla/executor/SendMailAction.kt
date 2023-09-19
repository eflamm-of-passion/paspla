package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.ActionData
import io.eflamm.paspla.model.action.sendmail.SendMailEntity
import io.eflamm.paspla.model.action.sendmail.SendMailInput
import io.eflamm.paspla.model.action.sendmail.SendMailOutput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender

class SendMailActionExecutor

var executeSendMailAction : (config: SendMailEntity, data: ActionData, mailSender: JavaMailSender, adapter: (config: SendMailEntity, data: ActionData) -> SendMailInput) -> ActionData =  {
        config, data, mailSender, adapter ->

    val logger: Logger = LoggerFactory.getLogger(SendMailActionExecutor::class.java)
    val input = adapter(config, data)

    logger.info("sending mail to ${input.recipients} ")

    val message = SimpleMailMessage()
    message.from  = input.sender
    message.setTo(input.recipients)
    message.subject = "Paspla"
    message.text  = input.body

    mailSender.send(message)

    SendMailOutput(success = true)

}