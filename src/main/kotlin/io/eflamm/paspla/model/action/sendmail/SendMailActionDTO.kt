package io.eflamm.paspla.model.action.sendmail

import java.util.*

data class SendMailActionDTO(
    val uuid: UUID?,
    val rank: Int,
    val sender: String,
    val recipients: String,
    val carbonCopyRecipients: String,
    val invisibleCarbonCopyRecipients: String,
    val attachmentFilename: String,
    val workflowUuid: UUID?
)
