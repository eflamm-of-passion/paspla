package io.eflamm.paspla.model.action.sendmail

open class SendMailInput(
    open val sender: String,
    open val recipients: String,
    open val carbonCopyRecipients: String?,
    open val invisibleCarbonCopyRecipients: String?,
    open val attachmentFilename: String?,
    open val body: String?
)
