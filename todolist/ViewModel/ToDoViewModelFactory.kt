package com.deepakverma.todolist.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ToDoViewModelFactory(private val repository: ToDoRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ToDoViewModel(repository) as T
    }
}