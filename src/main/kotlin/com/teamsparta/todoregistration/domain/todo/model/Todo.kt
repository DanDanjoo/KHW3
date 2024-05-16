package com.teamsparta.todoregistration.domain.todo.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
data class Todo(

    @Column(name= "title")
    var title: String,

    @Column(name= "description")
    var description: String? = null,

    @Column(name= "userid")
    var userId: Long,

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now(),

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    constructor(description: String, title: String) : this("", "", 0)
}