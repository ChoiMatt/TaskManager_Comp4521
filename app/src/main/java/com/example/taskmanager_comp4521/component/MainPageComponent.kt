package com.example.taskmanager_comp4521.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager_comp4521.Task

@Composable
fun MainPageComponent(
    tasks: List<Task>,
    menuBTNonClick: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ){
        Column(
            modifier = Modifier.weight(3f)
        ){
            HeaderComponent(menuBTNonClick, navController)
            AppOverviewComponent()
        }

        Column(
            modifier = Modifier.weight(16f)
        ){
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    bottom = 16.dp
                )
            ){
                tasks.forEach { task ->
                    val index = tasks.indexOf(task)
                    item{
                        if (index % 2 == 0){
                            TaskStructureComponent(task, bgcolor = MaterialTheme.colorScheme.primaryContainer)
                        }
                        else{
                            TaskStructureComponent(task, bgcolor = MaterialTheme.colorScheme.secondaryContainer)
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ){
            AddTaskBtnComponent(navController)
        }
    }
}