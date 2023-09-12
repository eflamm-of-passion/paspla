package io.eflamm.paspla.model.action.sendmail

import io.eflamm.paspla.model.action.ActionOutput

data class SendMailOutput(
    val success: Boolean,
) : ActionOutput
