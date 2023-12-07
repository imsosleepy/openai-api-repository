package com.openai.model.dto.openai.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ResponseChatDto(
        val id: String?,
        val `object`: String?,
        val created: Long,
        val model: String?,
        val choices: List<ChatCompletionChoiceVo>?,
        val usage: UsageVo?
) {
    data class UsageVo(
            val promptTokens: Int?,
            val completionTokens: Int?,
            val totalTokens: Int?,
            val timestamp: String?
    )

    data class ChatCompletionChoiceVo(
            val index: Int?,
            val delta: ChatMessageVo?,
            val finishReason: String?
    )

    data class ChatMessageVo(
            val role: String?,
            val content: String?
    )
}