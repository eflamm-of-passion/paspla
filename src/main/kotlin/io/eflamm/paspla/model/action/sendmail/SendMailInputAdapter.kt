package io.eflamm.paspla.model.action.sendmail

import io.eflamm.paspla.model.action.ActionData
import io.eflamm.paspla.model.action.httprequest.HttpRequestActionOutput

data class SendMailInputAdapter(
    override val sender: String,
    override val recipients: String,
    override val carbonCopyRecipients: String,
    override val invisibleCarbonCopyRecipients: String,
    override val attachmentFilename: String,
    override val body: String?
) : SendMailInput(sender, recipients, carbonCopyRecipients, invisibleCarbonCopyRecipients, attachmentFilename, body) {

    companion object {
        // design pattern : factory method
        fun createInput(config: SendMailEntity, output: ActionData?): SendMailInput? {
            var createdSendMailInput : SendMailInput? = null
            when(output) {
                is HttpRequestActionOutput -> {
                    createdSendMailInput = SendMailInputAdapter(config, output)
                }
                is SendMailOutput -> {
                    // TODO
                }
                else -> {
                    // TODO
                }
            }
            return createdSendMailInput
        }
    }

    /**
     * From an HTTP request action
     */
    constructor(sendMailConfig: SendMailEntity, output: HttpRequestActionOutput): this(
        sender = sendMailConfig.sender,
        recipients = sendMailConfig.recipients,
        carbonCopyRecipients = sendMailConfig.recipients,
        invisibleCarbonCopyRecipients = sendMailConfig.recipients,
        attachmentFilename = sendMailConfig.attachmentFilename,
            body = """
                URL called : ${output.url}
                Status code response : ${output.code}
                Body:
                ${output.body}
                """.trimIndent()
    )

    // default
    constructor(sendMailConfig: SendMailEntity): this(
        sender = sendMailConfig.sender,
        recipients = sendMailConfig.recipients,
        carbonCopyRecipients = sendMailConfig.recipients,
        invisibleCarbonCopyRecipients = sendMailConfig.recipients,
        attachmentFilename = sendMailConfig.attachmentFilename,
        body = sendMailConfig.body
    )

}
