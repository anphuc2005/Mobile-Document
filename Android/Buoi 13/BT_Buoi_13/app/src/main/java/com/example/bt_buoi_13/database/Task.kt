package com.example.bt_buoi_13.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val title: String,
    val subTitle: String,
    val isCompleted: Boolean = false
) : Serializable
