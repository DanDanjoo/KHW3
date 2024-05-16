package com.teamsparta.todoregistration.domain.todo.extension

import com.teamsparta.todoregistration.domain.todo.dto.TodoResponse
import com.teamsparta.todoregistration.domain.todo.model.Todo

fun Todo.toResponse(): TodoResponse {
    return TodoResponse()
}

fun TodoResponse(): TodoResponse {
    TODO("Not yet implemented")
}

fun List<Todo>.toResponse(): List<TodoResponse> {
    return this.map { it.toResponse() }
}