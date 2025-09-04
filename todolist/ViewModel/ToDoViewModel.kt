package com.deepakverma.todolist.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepakverma.todolist.model.ToDoDataClass
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: ToDoRepo) : ViewModel() {
    val allList: StateFlow<List<ToDoDataClass>> = repository.getAllList.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun addList(title: String, description: String, priority: String, date: String) {
        viewModelScope.launch {
            repository.insertList(
                ToDoDataClass(
                    title = title, description = description,
                    priority = priority, date = date
                )
            )
        }
    }

    fun deleteList(todoObj: ToDoDataClass) {
        viewModelScope.launch {
            repository.deleteList(todoObj)
        }
    }

    fun updateList(todoObj: ToDoDataClass) {
        viewModelScope.launch {
            repository.updateList(todoObj)
        }
    }
}