package com.example.taskmanager_comp4521.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp

@Composable
fun SortComponent(onClick: () -> Unit) {
    Row(){
        Text(
            text = "Sort",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Sort",
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onClick)
        )
    }

}

@Composable
fun FilterComponent(onClick: () -> Unit) {
    Row(){
        Text(
            text = "Filter",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Icon(imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Filter",
            modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = onClick))
    }


}
@Composable
fun AppOverviewComponent() {
    Row(){
        Column(verticalArrangement = Arrangement.spacedBy(8.dp))
        {
            Text(
                text = "Hi User!",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "8 tasks for today Monday",
                style = MaterialTheme.typography.titleMedium,
                color = Gray
            )
        }
        FilterComponent(onClick = {

        })
        SortComponent(onClick = {

        })
    }
}
