package com.openai.model.dto

import org.springframework.web.multipart.MultipartFile

data class MessageRequestDto(val message: String)

data class FileRequestDto(val file: MultipartFile)

data class AssistantCreateRequestDto(val name: String, val instruction: String)

data class CreateMessageRequestDto(
        val role: String,
        val content: String
)

data class CreateRunsRequestDto(
        val assistantId: String
)


