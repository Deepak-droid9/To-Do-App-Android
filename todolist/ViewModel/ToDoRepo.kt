package com.deepakverma.todolist.ViewModel

import com.deepakverma.todolist.model.ToDoDao
import com.deepakverma.todolist.model.ToDoDataClass
import kotlinx.coroutines.flow.Flow

class ToDoRepo(private val todoDao: ToDoDao) {
    /*Exposing Flow of List<ToDoDataClass> to viewModel*/
    val getAllList: Flow<List<ToDoDataClass>> = todoDao.getAllList()

    /*Insert List*/
    suspend fun insertList(todoObj: ToDoDataClass) {
        todoDao.insertList(todoObj)
    }

    /*Update List*/
    suspend fun updateList(todoObj: ToDoDataClass) {
        todoDao.updateList(todoObj)
    }

    /*Delete*/
    suspend fun deleteList(todoObj: ToDoDataClass) {
        todoDao.deleteList(todoObj)
    }
}