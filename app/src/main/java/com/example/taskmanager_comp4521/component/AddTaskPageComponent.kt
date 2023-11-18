package com.example.taskmanager_comp4521.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager_comp4521.Screen

@Composable
fun AddTaskPageDescription() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(imageVector = Icons.Default.Add,
            contentDescription = "Add_Task",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .size(36.dp)
        )
        Text(
            text = "Add a task",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Text(
            text = "Fill the details below to add a task into your TODO",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black.copy(alpha = 0.8f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillTaskDetail(label : String, data: String, onChange: (String) -> Unit) {
    Text(
        text = "Task $label",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(top = 30.dp, start = 3.dp)
    )

    TextField(
        value = data,
        onValueChange = onChange,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

//@Composable
//fun SelectPriorityLevel() {
//    val priorityOptions = listOf("High", "Medium", "Low", "N/A")
//    var selectedPriority by remember { mutableStateOf(priorityOptions[0]) }
//
//    Box() {
//        DropdownMenu(
//            expanded = false,
//            onDismissRequest = { /* Handle dismiss if needed */ }
//        ) {
//            priorityOptions.forEach { priority ->
//                DropdownMenuItem(
//                    onClick = {
//                        selectedPriority = priority
//                        onPrioritySelected(priority)
//                    }
//                ) {
//                    Text(text = priority)
//                }
//            }
//        }
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable(onClick = { /* Handle opening the dropdown menu if needed */ })
//        ) {
//            Text(
//                text = selectedPriority,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.padding(8.dp)
//            )
//        }
//    }
//}

@Composable
fun ButtonRow(onCreateClicked: () -> Unit, onCancelClicked: () -> Unit, create_btn_text: String, cancel_btn_text: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = onCreateClicked,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp, end = 4.dp)
        ) {
            Text(text = create_btn_text)
        }

        Button(
            onClick = onCancelClicked,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp, end = 4.dp)
        ) {
            Text(text = cancel_btn_text)
        }
    }
}
@Composable
fun AddTaskPageComponent(navController: NavController) {
    val title = remember { mutableStateOf("") }
    val subject = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val tag = remember { mutableStateOf("") }
    val priority = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(14.dp)
    )
    {
        Column(modifier = Modifier
            .weight(3f)
        )
        {
            AddTaskPageDescription()
        }
        Column(modifier = Modifier
            .weight(16f)
        )
        {
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                item{
                    FillTaskDetail("Title", title.value, { title.value = it })
                }
                item{
                    FillTaskDetail("Subject", subject.value, { subject.value = it })
                }
                item{
                    FillTaskDetail("Description", description.value, { description.value = it })
                }
                item{
                    FillTaskDetail("Tag", tag.value, { tag.value = it })
                }
            }
        }
        Column(modifier = Modifier
            .weight(1f)
        )
        {
            ButtonRow(onCreateClicked = {},
                onCancelClicked = {navController.navigate(Screen.MainTaskScreen.route)},
                create_btn_text = "Create",
                cancel_btn_text = "Cancel")
        }
    }
}