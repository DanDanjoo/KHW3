package com.teamsparta.todoregistration.domain.todo.dto

import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val userid: Long,
    val createdAt: LocalDateTime = LocalDateTime.now()
)


