package com.openai.model.dto.openai

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class ChatRequestDto(
        // 사용할 GPT 모델
        val model: String,
        // 전달할 메시지 리스트
        val messages: List<ChatMessageVo>,
        // 최대 사용 토큰 제한
        @JsonProperty("max_tokens")
        val maxTokens: Int,
        // 답변 자유도
        val temperature: Double,
        // 사용자에게 전송 여부
        val stream: Boolean
) {
    data class ChatMessageVo(
            val role: String,
            val content: String
    )
}

data class ResponseChatDto(
        val id: String?,
        val `object`: String?,
        val created: Long,
        val model: String?,
        val choices: List<ChatCompletionChoiceVo>?,
        val usage: UsageVo?
) {
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class UsageVo(
            val promptTokens: Int?,
            val completionTokens: Int?,
            val totalTokens: Int?,
            val timestamp: String?
    )
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
    data class ChatCompletionChoiceVo(
            val index: Int?,
            val message: ChatMessageVo?,
            val finishReason: String?
    )

    data class ChatMessageVo(
            val role: String?,
            val content: String?
    )
}


