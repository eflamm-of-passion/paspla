package io.eflamm.paspla.executor

import io.eflamm.paspla.model.action.sendmail.SendMailInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component


@Component
class SendMailActionExecutor : ActionExecutor<SendMailInput, String> {

    @Autowired
    private lateinit var mailSender: JavaMailSender

    override fun process(input: SendMailInput): String {
        println("sending mail to ${input.recipients} ")

        val message = SimpleMailMessage()
        message.from  = input.sender
        message.setTo(input.recipients)
        message.subject = "Paspla"
        message.text  = input.body

        mailSender.send(message)

        return ""
    }
}