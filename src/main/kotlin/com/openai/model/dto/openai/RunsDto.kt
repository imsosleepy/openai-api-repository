package com.openai.model.dto.openai

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class RunsRequestDto(
        @JsonProperty("assistant_id")
        val assistantId: String,
        val model: String?,
        val instructions: String?,
        val tools: ArrayList<String>?,
        val metadata: Any?
) {
    constructor(assistantId: String): this(assistantId, model=null, instructions=null, tools=null, metadata=null)
}

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class RunsResponseDto(
        val id: String,
        val `object`: String,
        val createdAt: Long,
        val assistantId: String,
        val threadId: String,
        val status: String,
        val startedAt: Long,
        val expiresAt: Long?,
        val cancelledAt: Long?,
        val failedAt: Long?,
        val completedAt: Long?,
        val lastError: Any?,
        val model: String,
        val instructions: Any?,
        val tools: List<Tool>,
        val fileIds: List<String>,
        val metadata: Map<String, Any>
) {
    data class Tool(
            val type: String
    )
}