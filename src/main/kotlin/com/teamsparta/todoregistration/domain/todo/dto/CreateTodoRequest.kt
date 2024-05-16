package com.teamsparta.todoregistration.domain.todo.dto
import java.time.LocalDateTime

data class CreateTodoRequest(
    val title: String,
    val description: String,
    val userid: Long,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
