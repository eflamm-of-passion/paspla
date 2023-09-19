package io.eflamm.paspla.model.workflow

data class WorkflowInsertDTO(
    val name: String,
    val description: String,
    val isEnabled: Boolean
)