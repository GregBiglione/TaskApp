package com.example.taskapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskapp.domain.model.Task

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isDone: Boolean
) {
    fun toTask() = Task(
        id,
        title = title,
        isDone = isDone
    )

    companion object {
        fun from(task: Task) = TaskEntity(
            id = task.id,
            title = task.title,
            isDone = task.isDone
        )
    }
}
