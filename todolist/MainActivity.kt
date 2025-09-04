package com.deepakverma.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.deepakverma.todolist.ViewModel.ToDoRepo
import com.deepakverma.todolist.ViewModel.ToDoViewModel
import com.deepakverma.todolist.model.AppDataBase
import com.deepakverma.todolist.ui.theme.ToDoListTheme
import com.deepakverma.todolist.view.NavigationControl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = AppDataBase.getDatabase(applicationContext)
        val repo = ToDoRepo(db.todoDao())
        val viewModel = ToDoViewModel(repo)

        setContent {
            ToDoListTheme {
                NavigationControl(viewModel = viewModel)
            }
        }
    }
}


