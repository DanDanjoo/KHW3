package com.teamsparta.todoregistration.domain.todo.dto

data class CreateTodoRequest(
    val title: String,
    val description: String,
    val userid: Long,
)
