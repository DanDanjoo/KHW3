package com.teamsparta.todoregistration.domain.todo.service

import com.teamsparta.todoregistration.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoregistration.domain.todo.dto.TodoResponse
import com.teamsparta.todoregistration.domain.todo.dto.UpdateTodoRequest


interface TodoService {
    fun getAlltodoList(): List<TodoResponse>
    fun getTodoById(id: Long): TodoResponse
    fun createTodo(request: CreateTodoRequest): TodoResponse
    fun updateTodo(id: Long, request: UpdateTodoRequest): TodoResponse
    fun deleteTodo(id: Long)
}