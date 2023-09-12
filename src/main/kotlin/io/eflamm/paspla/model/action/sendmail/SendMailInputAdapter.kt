package io.eflamm.paspla.model.action.sendmail

import io.eflamm.paspla.model.action.httprequest.HttpRequestActionOutput

data class SendMailInputAdapter(
    override val sender: String,
    override val recipients: String,
    override val carbonCopyRecipients: String,
    override val invisibleCarbonCopyRecipients: String,
    override val attachmentFilename: String,
    override val body: String?
) : SendMailInput(sender, recipients, carbonCopyRecipients, invisibleCarbonCopyRecipients, attachmentFilename, body) {

    /**
     * From an HTTP request action
     */
    constructor(sendMailConfig: SendMailConfig, output: HttpRequestActionOutput): this(
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

}
