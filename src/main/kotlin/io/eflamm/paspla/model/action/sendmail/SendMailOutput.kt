package io.eflamm.paspla.model.action.sendmail

import io.eflamm.paspla.model.action.ActionData

data class SendMailOutput(
    val success: Boolean,
) : ActionData
