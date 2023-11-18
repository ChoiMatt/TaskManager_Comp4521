package com.example.taskmanager_comp4521

sealed class Screen(val route: String){
    object MainTaskScreen: Screen("main_task_screen")
    object AddTaskScreen: Screen("add_task_screen")
    object SettingScreen: Screen("setting_screen")
}
