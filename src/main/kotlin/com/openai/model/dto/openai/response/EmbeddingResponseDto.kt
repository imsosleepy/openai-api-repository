package com.openai.model.dto.openai.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy::class)
data class EmbeddingResponseDto(
        val model: String,
        val `object`: String,
        val data: List<EmbeddingVo>,
        val usage: UsageVo
) {

    data class EmbeddingVo(
            val `object`: String?,
            val embedding: FloatArray, // lateinit 대신 직접 선언
            val index: Int?
    )

    data class UsageVo(
            val promptTokens: Long,
            val completionTokens: Long,
            val totalTokens: Long,
            val timestamp: String?
    )
}
