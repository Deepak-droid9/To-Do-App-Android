package com.deepakverma.todolist.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.deepakverma.todolist.ViewModel.ToDoViewModel
import com.deepakverma.todolist.model.ToDoDataClass


//@Preview(showSystemUi = true)
@Composable
fun PreviewFunction() {

}


@Composable
fun NavigationControl(viewModel: ToDoViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable(route = "home") {
            ToDoListScreen(navController, viewModel = viewModel)
        }

        composable("addTask") { AddTaskScreen(navController, viewModel = viewModel) }
        composable(
            "updateTask/{id}/{title}/{description}/{date}/{priority}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
                navArgument("priority") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val args = backStackEntry.arguments!!
            val todo = ToDoDataClass(
                id = args.getInt("id"),
                title = args.getString("title") ?: "",
                description = args.getString("description") ?: "",
                priority = args.getString("priority") ?: "",
                date = args.getString("date") ?: ""
            )
            UpdateTask(navController, todo, viewModel)
        }
    }
}