package com.openai.controller

import com.openai.client.AssistantsClient
import com.openai.model.dto.openai.MessagesListResponseDto
import com.openai.model.dto.openai.MessagesRequestDto
import com.openai.model.dto.openai.RunsRequestDto
import com.openai.model.dto.openai.RunsResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration
import java.time.Instant
import java.util.concurrent.*

@RestController
class RunStatusController(
    private val assistantsClient: AssistantsClient
) {
    @GetMapping("/create/chat")
    fun createChat(): String {
        try {
            val assistantId = "[Your Assistant Id]";
            val threads = assistantsClient.createThreads()
            assistantsClient.createMessages(threads.id, MessagesRequestDto("user", "This is question"))
            val run = assistantsClient.createRuns(threads.id, RunsRequestDto(assistantId))
            assistantsClient.getRun(threads.id, run.id)

            val runsResponseDtoFuture: CompletableFuture<RunsResponseDto> =
                asyncRunRequest(threads.id, run.id)
            val d: MessagesListResponseDto? = if (runsResponseDtoFuture.get() != null) {
                assistantsClient.getMessagesList(threads.id)
            } else {
                null
            }

            val response = d?.data?.get(0)?.content?.get(0)?.text?.value

            return response ?: ""
        } catch (e: Exception) {
            println(e.message)
            return "error"
        }

    }

    fun asyncRunRequest(threadId: String, runId: String): CompletableFuture<RunsResponseDto> {
        val startTime = Instant.now()
        val future = CompletableFuture<RunsResponseDto>()
        val scheduler: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
        val scheduledFuture: ScheduledFuture<*> = scheduler.scheduleAtFixedRate({
            try {
                val response: RunsResponseDto = assistantsClient.getRun(threadId, runId)
                println("retrying............")
                if (response.status == "completed") {
                    future.complete(response)
                }
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }, 0, 1, TimeUnit.SECONDS)

        future.whenComplete { _, _ ->
            scheduledFuture.cancel(true)
            val endTime = Instant.now()
            val duration = Duration.between(startTime, endTime)
            println("asyncRunRequest executed in ${duration.toMillis()} milliseconds")
        }
        return future
    }

    fun loopRunRequest(threadId: String, runId: String) {
        val startTime = Instant.now()
        while (true) {
            try {
                Thread.sleep(2000);
                val response: RunsResponseDto = assistantsClient.getRun(threadId, runId)
                println("retrying............")
                if (response.status == "completed") {
                    break;
                }
            } catch (e: Exception) {
                println("error")
                return;
            }
        }
        val endTime = Instant.now()
        val duration = Duration.between(startTime, endTime)
        println("asyncRunRequest executed in ${duration.toMillis()} milliseconds")
    }
}