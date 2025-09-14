package com.example.bt_buoi_13.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Xung đột id thì sẽ thay thế bằng bản ghi mới
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM task_table WHERE isCompleted = 0 ORDER BY id DESC ")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE isCompleted = 1")
    fun getCompletedTasks(): LiveData<List<Task>>
}
