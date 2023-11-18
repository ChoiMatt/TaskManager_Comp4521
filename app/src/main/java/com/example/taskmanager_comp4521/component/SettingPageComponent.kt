package com.example.taskmanager_comp4521.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager_comp4521.Screen

@Composable
fun SettingPageDescription() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(imageVector = Icons.Default.Settings,
            contentDescription = "Add_Task",
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .size(36.dp)
        )

        Text(
            text = "Setting Menu",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,

            )

        Text(
            text = "Set Your User Preference",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black.copy(alpha = 0.8f),
        )
    }
}

@Composable
fun SettingPageComponent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        SettingPageDescription()
        ToggleButtonComponent(
            iconOn = Icons.Default.Check,
            iconOff = Icons.Default.Close
        )
        ButtonRow(onCreateClicked = {},
            onCancelClicked = {navController.navigate(Screen.MainTaskScreen.route)},
            create_btn_text = "Save",
            cancel_btn_text = "Cancel")
    }


}