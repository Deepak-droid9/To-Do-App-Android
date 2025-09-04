package com.deepakverma.todolist.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.deepakverma.todolist.R
import com.deepakverma.todolist.ViewModel.ToDoViewModel
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController,
    viewModel: ToDoViewModel
) {

    val primaryColor = colorResource(id = R.color.primary_color)


    // UI states
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    //priority radio button
    val priorityOptions = listOf("High", "Medium", "Low")
    var priority by remember { mutableStateOf(priorityOptions[0]) }

    //date picker
    val datePickerState = rememberDatePickerState()
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Task") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryColor,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBackIosNew, contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
//                            .border(2.dp, Color.Blue),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        tint = primaryColor
                    )
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                minLines = 3,
                maxLines = 3,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                leadingIcon = {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = null,
                        tint = primaryColor
                    )
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = selectedDate,
                onValueChange = {},
                readOnly = true,
                label = { Text("Date") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Select Date",
                        tint = primaryColor,
                        modifier = Modifier.clickable { showDialog = true })
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            if (showDialog) {
                DatePickerDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                datePickerState.selectedDateMillis?.let {
                                    selectedDate = formatDate(it)
                                }
                                showDialog = false
                            }) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancel")
                        }
                    }) {
                    DatePicker(state = datePickerState)
                }
            }
            Spacer(Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(" Select Priority", style = MaterialTheme.typography.titleMedium)
                Row {
                    priorityOptions.forEach { opt ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            RadioButton(
                                selected = (priority == opt),
                                onClick = { priority = opt })
                            Text(text = opt, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank() && priority.isNotBlank() && selectedDate.isNotBlank()) {
                        viewModel.addList(title, description, priority, selectedDate)

                        title = ""
                        description = ""
                        selectedDate = ""
                        navController.popBackStack()
                        Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor, contentColor = Color.White
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(16.dp),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(200.dp)
            ) {
                Text("Add")
            }
        }

    }
}


@SuppressLint("SimpleDateFormat")
fun formatDate(millis: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy")
    return sdf.format(Date(millis))
}


