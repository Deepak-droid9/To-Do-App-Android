package com.deepakverma.todolist.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    /*Read All List*/
    @Query("SELECT * FROM todo_table")
    fun getAllList(): Flow<List<ToDoDataClass>>

    /*Insert list*/
    @Insert
    suspend fun insertList(todoObj: ToDoDataClass)

    /*Update*/
    @Update
    suspend fun updateList(todoObj: ToDoDataClass)

    /*Delete*/
    @Delete
    suspend fun deleteList(todoObj: ToDoDataClass)
}