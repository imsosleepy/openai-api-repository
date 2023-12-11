package com.openai.model.dto.openai

data class MessageRequestDto(val message: String)

data class AssistantCreateRequestDto(val name: String, val instruction: String)