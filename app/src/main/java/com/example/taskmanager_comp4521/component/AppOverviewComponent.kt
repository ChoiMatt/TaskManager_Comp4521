package com.example.taskmanager_comp4521.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.taskmanager_comp4521.datastore.StoreUserPreference

@Composable
fun SortComponent() {
    Box(){
        var expanded by remember { mutableStateOf(false) }
        Button(
            onClick = { expanded = true }
        )
        {
            Row(horizontalArrangement = Arrangement.Center){
                Text(
                    text = "Sort",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.scrim
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Options",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {  Text("By Priority") },
                onClick = { /* Handle refresh! */ }
            )
            Divider()
            DropdownMenuItem(
                text = { Text("By Due Date") },
                onClick = { /* Handle settings! */ }
            )
            Divider()
            DropdownMenuItem(
                text = { Text("By Progress") },
                onClick = { /* Handle send feedback! */ }
            )
        }
    }
}

@Composable
fun FilterComponent() {
    Box(
        modifier = Modifier.wrapContentSize()
    ){
        var expanded by remember { mutableStateOf(false) }
        Button(
            onClick = { expanded = true }
        ){
            Row(horizontalArrangement = Arrangement.Center){
                Text(
                    text = "Filter",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.scrim
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Options",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {  Text("By Priority") },
                onClick = { /* Handle refresh! */ }
            )
            Divider()
            DropdownMenuItem(
                text = { Text("By Due Date") },
                onClick = { /* Handle settings! */ }
            )
            Divider()
            DropdownMenuItem(
                text = { Text("By Progress") },
                onClick = { /* Handle send feedback! */ }
            )
        }
    }
}
@Composable
fun AppOverviewComponent() {
    val context = LocalContext.current
    val dataStore = StoreUserPreference(context)
    val savedName = dataStore.getUserName.collectAsState(initial = "")
    val name = if (savedName.value.isNullOrEmpty()) "User" else savedName.value
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(top = 8.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp)
        )
        {
            Text(
                modifier = Modifier.weight(1f),
                //text = "Hi User!",
                text = "Hi $name!",
                style = MaterialTheme.typography.headlineMedium
            )
            FilterComponent()
            SortComponent()
        }
        Text(
            text = "8 tasks for today Monday",
            style = MaterialTheme.typography.titleMedium,
            color = Gray
        )
  }
}

//@Preview
//@Composable
//fun SimpleComposablePreview() {
//    SortComponent()
//}
//
