package com.teamsparta.todoregistration.domain.todo.controller

import com.teamsparta.todoregistration.domain.todo.dto.CreateTodoRequest
import com.teamsparta.todoregistration.domain.todo.dto.TodoResponse
import com.teamsparta.todoregistration.domain.todo.dto.UpdateTodoRequest
import com.teamsparta.todoregistration.domain.todo.service.TodoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/todo")
@Tag(name = "Todo", description = "Todo management APIs")
class TodoListController (
    private val todoService: TodoService
) {

    @PostMapping()
    @Operation(summary = "Todo 만들기", description = "새로운 Todo 항목을 생성합니다.")
    fun createTodo(@RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoRequest))
    }


    @GetMapping("/{id}")
    @Operation(summary = "ID로 Todo 가져오기", description = "ID로 Todo 항목을 검색합니다.")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(todoService.getTodoById(id))
    }


    @PutMapping("/{id}")
    @Operation(summary = "ID로 Todo 수정", description = "ID로 Todo 항목을 수정합니다.")
    fun updateTodo(
        @PathVariable id: Long,
        @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
       return ResponseEntity
           .status(HttpStatus.OK)
           .body(todoService.updateTodo(id, updateTodoRequest))
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ID로 Todo 삭제", description = "ID로 Todo 항목을 삭제합니다.")
    fun deleteTodo(@PathVariable id: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(id)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }



}





