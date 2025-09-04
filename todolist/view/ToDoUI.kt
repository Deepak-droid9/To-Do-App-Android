package com.deepakverma.todolist.view

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deepakverma.todolist.R
import com.deepakverma.todolist.ViewModel.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    navController: NavController,
    viewModel: ToDoViewModel
) {

    val primaryColor = colorResource(id = R.color.primary_color)
    val cardColor = colorResource(id = R.color.card_color)

    /*Collect todo list from ViewModel*/
    val todos by viewModel.allList.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("To Do App", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryColor, titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Default.DoneAll, contentDescription = null, tint = Color.White
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
//                    .border(2.dp, Color.Black)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(40.dp)),
                containerColor = Color.Transparent,

                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        FloatingActionButton(
                            onClick = { navController.navigate("addTask") },
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                            containerColor = primaryColor,
                            contentColor = Color.White,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(100.dp)
                        ) {
                            Icon(
                                Icons.Default.Add, contentDescription = "Add", Modifier.size(
                                    32.dp,
                                )
                            )
                        }
                    }
                },

                // containerColor = colorResource(id = R.color.light_pink)
            )
        },
    ) { innerPadding ->

        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(todos) { todo ->
                TaskCardDesign(
                    todo = todo,
                    onDelete = { viewModel.deleteList(it) },
                    onUpdate = {
                        navController.navigate(
                            "updateTask/" +
                                    "${todo.id}/" +
                                    "${Uri.encode(todo.title)}/" +
                                    "${Uri.encode(todo.description)}/" +
                                    "${Uri.encode(todo.date)}/" +
                                    "${Uri.encode(todo.priority)}"
                        )
                    },
                    cardColor = cardColor,
                    primaryColor = primaryColor
                )
            }
        }
    }
}

