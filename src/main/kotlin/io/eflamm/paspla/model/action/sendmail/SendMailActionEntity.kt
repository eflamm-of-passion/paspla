package io.eflamm.paspla.model.action.sendmail

import io.eflamm.paspla.model.job.JobEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "send_mail_actions")
data class SendMailActionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()", insertable = false, updatable = false)
    val uuid: UUID?,
    val rank: Int,
    val sender: String,
    val recipients: String,
    @Column(name = "carbon_copy_recipients")
    val carbonCopyRecipients: String,
    @Column(name = "invisible_carbon_copy_recipients")
    val invisibleCarbonCopyRecipients: String,
    val attachmentFilename: String,
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    val job: JobEntity?
) { // TODO create the classes

    constructor() : this(
        id = null,
        uuid = null,
        rank = 0,
        sender = "",
        recipients = "",
        carbonCopyRecipients = "",
        invisibleCarbonCopyRecipients = "",
        attachmentFilename = "",
        job = null
    )

}
