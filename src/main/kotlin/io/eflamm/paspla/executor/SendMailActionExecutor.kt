package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.sendmail.SendMailInput
import io.eflamm.paspla.model.action.sendmail.SendMailOutput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component


@Component
class SendMailActionExecutor : ActionExecutor<SendMailInput, SendMailOutput> {

    private val logger: Logger = LoggerFactory.getLogger(SendMailActionExecutor::class.java)

    @Autowired
    private lateinit var mailSender: JavaMailSender

    override fun process(input: SendMailInput): SendMailOutput {
        logger.info("sending mail to ${input.recipients} ")

        val message = SimpleMailMessage()
        message.from  = input.sender
        message.setTo(input.recipients)
        message.subject = "Paspla"
        message.text  = input.body

        mailSender.send(message)

        return SendMailOutput(success = true)
    }
}