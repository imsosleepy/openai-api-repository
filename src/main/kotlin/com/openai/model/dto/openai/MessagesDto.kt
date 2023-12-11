package com.openai.model.dto.openai

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class MessagesListRequestDto(
        val limit: String?,
        val order: String?,
        val after: String?,
        val before: String?
) {
    constructor(): this(limit=null,order=null,after=null,before=null)
}

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MessagesListResponseDto(
        val `object`: String,
        val data: List<MessageData>,
        val firstId: String,
        val lastId: String,
        val hasMore: Boolean
) {
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class MessageData(
            val id: String,
            val `object`: String,
            val createdAt: Long,
            val threadId: String,
            val role: String,
            val content: List<Content>,
            val fileIds: List<String>,
            val assistantId: String?,
            val runId: String?,
            val metadata: Map<String, Any>
    ) {
        data class Content(
                val type: String,
                val text: Text
        )

        data class Text(
                val value: String,
                val annotations: List<Any>
        )
    }
}


@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class MessagesRequestDto(
        val role: String,
        val content: String,
        @JsonProperty("file_ids")
        val fileIds: ArrayList<String>?,
        val metadata: Any?
) {
    constructor(role: String, content: String) : this(role, content, fileIds = null, metadata = null)
}

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class MessagesResponseDto(
        val id: String,
        val `object`: String,
        val createdAt: Long,
        val threadId: String,
        val role: String,
        val content: List<MessageContent>,
        val fileIds: List<String>,
        val assistantId: String?,
        val runId: String?,
        val metadata: Map<String, Any>
) {
    data class MessageContent(
            val type: String,
            val text: TextContent
    )

    data class TextContent(
            val value: String,
            val annotations: List<Any>
    )
}