package com.openai.model.dto

data class CreateMessageRequestDto(
        val role: String,
        val content: String
)

data class CreateRunsRequestDto(
        val assistantId: String
)
