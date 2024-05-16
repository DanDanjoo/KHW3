package com.teamsparta.todoregistration.domain.todo.repository

import com.teamsparta.todoregistration.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository


interface TodoRepository : JpaRepository<Todo, Long> {}