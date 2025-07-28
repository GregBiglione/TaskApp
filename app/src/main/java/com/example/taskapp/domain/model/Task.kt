package com.example.taskapp.domain.model

data class Task(
    val id: Int = 0,
    val title: String,
    val isDone: Boolean = false
)