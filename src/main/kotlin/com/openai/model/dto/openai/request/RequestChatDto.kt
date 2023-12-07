package com.openai.model.dto.openai.request

import com.fasterxml.jackson.annotation.JsonProperty

data class RequestChatDto(
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
