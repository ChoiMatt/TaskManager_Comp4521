package com.example.taskmanager_comp4521.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager_comp4521.R
import com.example.taskmanager_comp4521.Screen

@Composable
fun HeaderComponent(menuBTNonClick: () -> Unit, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically)
    {
        Icon(imageVector = Icons.Filled.Menu,
            contentDescription = "Menu",
            modifier = Modifier
                .padding(end= 16.dp)
                .clickable(onClick = menuBTNonClick)
        )
        Image(painter = painterResource(id = R.drawable.wonder_woman),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        )
        Icon(imageVector = Icons.Filled.Settings,
            contentDescription = "Settings",
            modifier = Modifier
                .padding(end= 16.dp)
                .clickable(onClick = {navController.navigate(Screen.SettingScreen.route)})
        )
    }
}

