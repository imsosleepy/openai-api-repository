package com.openai.model.dto.openai

import org.springframework.web.multipart.MultipartFile

data class MessageRequestDto(val message: String)

data class FileRequestDto(val file: MultipartFile)

data class AssistantCreateRequestDto(val name: String, val instruction: String)