package com.example.taskmanager_comp4521.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.taskmanager_comp4521.Screen
import com.example.taskmanager_comp4521.datastore.StoreUserPreference
import kotlinx.coroutines.launch

@Composable
fun PhotoPicker() : Uri{
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri = uri }
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            singlePhotoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "Pick Profile Picture")
        }
        AsyncImage(
            model = selectedImageUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 30.dp)
                .size(width = 90.dp, height = 90.dp) // Set desired size
                .clip(shape = CircleShape) // Apply circular shape
        )
    }
    return selectedImageUri?:Uri.parse("android.resource://com.example.taskmanager_comp4521/drawable/default_user")
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNameInput(label : String, data: String?, onChange: (String) -> Unit){
    TextField(
        value = data?:"",
        onValueChange = onChange,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

@Composable
fun SettingPageDescription() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Icon(imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
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
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black.copy(alpha = 0.8f),
        )
    }
}

@Composable
fun SettingPageComponent(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreUserPreference(context)
    val savedName = dataStore.getUserName.collectAsState(initial = "")
    val darkTheme = dataStore.getThemePreference.collectAsState(initial = false)
    var user_name by remember { mutableStateOf(savedName.value) }
    var is_dark_theme by remember { mutableStateOf(darkTheme.value) }
    var dark_theme = false
    var uri: Uri? = null
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column(modifier = Modifier.weight(1f)) {
            SettingPageDescription()
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Theme:",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black.copy(alpha = 0.8f)

                )
                dark_theme = ToggleButtonComponent(
                    size = 60.dp,
                    iconOn = Icons.Default.Nightlight,
                    iconOff = Icons.Default.LightMode,
                    target = is_dark_theme
                )
            }

            UserNameInput(label = "User Name", data = user_name, onChange = {user_name = it})
            uri = PhotoPicker()
        }

        ButtonRow(
            onCreateClicked = {
                scope.launch { 
                    dataStore.savePreference(user_name?:"", dark_theme, uri.toString())
                }
                navController.navigate(Screen.MainTaskScreen.route)
        },
            onCancelClicked = {navController.navigate(Screen.MainTaskScreen.route)},
            create_btn_text = "Save",
            cancel_btn_text = "Cancel")
    }


}