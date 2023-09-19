package io.eflamm.paspla.model.action.sendmail

import io.eflamm.paspla.model.action.ActionConfig
import io.eflamm.paspla.model.workflow.WorkflowEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "send_mail_actions")
data class SendMailEntity(
    override val id: Long? = null,
    override val uuid: UUID? = null,
    override var rank: Int,
    val sender: String,
    val recipients: String,
    @Column(name = "carbon_copy_recipients")
    val carbonCopyRecipients: String,
    @Column(name = "invisible_carbon_copy_recipients")
    val invisibleCarbonCopyRecipients: String,
    val attachmentFilename: String,
    val body: String,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "workflow_id", referencedColumnName = "id")
    val workflow: WorkflowEntity?
) : ActionConfig(id, uuid, rank) {

    constructor() : this(
        id = null,
        uuid = null,
        rank = 0,
        sender = "",
        recipients = "",
        carbonCopyRecipients = "",
        invisibleCarbonCopyRecipients = "",
        attachmentFilename = "",
        body = "",
        workflow = null
    )

}
