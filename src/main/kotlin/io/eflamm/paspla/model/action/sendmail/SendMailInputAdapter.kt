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
    constructor(sendMailAConfiguration: SendMailActionConfigEntity, httpResponseOutput: HttpRequestActionOutput): this(
        sender = sendMailAConfiguration.sender,
        recipients = sendMailAConfiguration.recipients,
        carbonCopyRecipients = sendMailAConfiguration.recipients,
        invisibleCarbonCopyRecipients = sendMailAConfiguration.recipients,
        attachmentFilename = sendMailAConfiguration.attachmentFilename,
            body = """
                URL called : ${httpResponseOutput.url}
                Status code response : ${httpResponseOutput.code}
                Body:
                ${httpResponseOutput.body}
                """.trimIndent()
    )
}
