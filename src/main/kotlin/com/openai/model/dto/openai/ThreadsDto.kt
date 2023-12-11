package com.openai.model.dto.openai

import com.fasterxml.jackson.annotation.JsonProperty

data class ThreadsResponseDto (
        val id: String,
        val `object`: String,
        @JsonProperty("created_at")
        val createdAt: Long,
        val metadata: Any
)

data class ThreadsDeleteResponseDto (
        val id: String,
        val `object`: String,
        val deleted: String
)