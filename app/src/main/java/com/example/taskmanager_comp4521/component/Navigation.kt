package com.example.taskmanager_comp4521.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager_comp4521.Screen
import com.example.taskmanager_comp4521.Task

@Composable
fun Navigation(tasks: List<Task>,
               menuBTNonClick: () -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainTaskScreen.route ){
        composable(route = Screen.MainTaskScreen.route){
            MainPageComponent(tasks = tasks, menuBTNonClick = menuBTNonClick, navController = navController)
        }

        composable(route = Screen.AddTaskScreen.route){
            AddTaskPageComponent(navController = navController)
        }

        composable(route = Screen.SettingScreen.route){
            SettingPageComponent(navController = navController)
        }
    }
}