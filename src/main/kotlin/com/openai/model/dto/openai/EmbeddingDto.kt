package com.openai.model.dto.openai

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming


data class EmbeddingRequestDto (
        val model: String,
        val input: List<String>,
)

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class EmbeddingResponseDto(
        val model: String,
        val `object`: String,
        val data: List<EmbeddingVo>,
        val usage: UsageVo
) {

    data class EmbeddingVo(
            val `object`: String?,
            val embedding: FloatArray,
            val index: Int?
    )

    data class UsageVo(
            val promptTokens: Long,
            val completionTokens: Long,
            val totalTokens: Long,
            val timestamp: String?
    )
}
