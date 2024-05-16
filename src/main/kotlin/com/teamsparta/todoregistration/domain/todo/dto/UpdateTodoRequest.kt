package com.teamsparta.todoregistration.domain.todo.dto

data class UpdateTodoRequest(
    val title: String,
    val description: String,
    val userid: Long
)