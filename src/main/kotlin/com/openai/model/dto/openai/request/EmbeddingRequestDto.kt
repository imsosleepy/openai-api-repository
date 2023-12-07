package com.openai.model.dto.openai.request


data class EmbeddingRequestDto (
    val model: String,
    val input: List<String>,
)
