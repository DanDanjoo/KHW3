package com.teamsparta.todoregistration.domain.todo.service

import com.teamsparta.todoregistration.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoregistration.domain.todo.dto.TodoResponse
import com.teamsparta.todoregistration.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todoregistration.domain.todo.model.Todo
import com.teamsparta.todoregistration.domain.todo.extension.toResponse
import com.teamsparta.todoregistration.domain.todo.repository.TodoRepository
import com.teamsparta.todoregistration.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
) : TodoService {
    override fun getAlltodoList(): List<TodoResponse> {
        return todoRepository.findAll().toResponse()
    }

    override fun getTodoById(id: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            Todo(
                title = request.title,
                description = request.description,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(id: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        val (title, description) = request

        todo.title = title
        todo.description = description

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(id: Long) {
        val todo = todoRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("todo", id)
        todoRepository.delete(todo)
    }




}