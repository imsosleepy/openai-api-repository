package com.openai.model.dto.openai

import com.fasterxml.jackson.annotation.JsonInclude

data class AssistantDto(
        val id: String?,
        val `object`: String?,
        val createdAt: Long,
        val name: String?,
        val description: String?,
        val model: String?,
        val instructions: String?,
        val tools: List<Tool>?,
        val fileIds: Any?, // 여기서 Any?를 사용하면 모든 타입을 처리할 수 있습니다.
        val metadata: Any? // 여기서 Any?를 사용하면 모든 타입을 처리할 수 있습니다.
) {
    data class Tool(
        val type: String?
    )
}

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class AssistantsRequestDto(
        val model: String,
        val name: String?,
        val instructions: String?,
        val tools: List<Tool>?
) {
    data class Tool(
            val type: String?
    )
    constructor(model: String, name: String?, instructions: String?): this(model, name, instructions, tools= null)
}
