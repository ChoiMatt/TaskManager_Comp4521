package com.example.taskmanager_comp4521.component

import android.net.Uri
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.taskmanager_comp4521.Screen
import com.example.taskmanager_comp4521.datastore.StoreUserPreference


@Composable
fun HeaderComponent(menuBTNonClick: () -> Unit, navController: NavController) {
    val context = LocalContext.current
    val dataStore = StoreUserPreference(context)
    val pictureURI = dataStore.getProfilePicture.collectAsState(initial = "").value
    val targetURI = if (pictureURI.isNullOrEmpty()) Uri.parse("android.resource://com.example.taskmanager_comp4521/drawable/default_user") else Uri.parse(pictureURI.toString())
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
        AsyncImage(
            model = targetURI,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 50.dp, height = 50.dp) // Set desired size
                .clip(shape = CircleShape) // Apply circular shape
        )

        Icon(imageVector = Icons.Filled.Settings,
            contentDescription = "Settings",
            modifier = Modifier
                .padding(end= 16.dp)
                .clickable(onClick = {navController.navigate(Screen.SettingScreen.route)})
        )
    }
}

