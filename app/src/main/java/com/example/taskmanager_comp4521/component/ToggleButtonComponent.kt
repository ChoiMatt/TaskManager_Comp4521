package com.example.taskmanager_comp4521.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.taskmanager_comp4521.datastore.StoreUserPreference
import com.example.taskmanager_comp4521.ui.theme.md_theme_dark_primary
import com.example.taskmanager_comp4521.ui.theme.md_theme_dark_primaryContainer
import com.example.taskmanager_comp4521.ui.theme.md_theme_light_primary
import com.example.taskmanager_comp4521.ui.theme.md_theme_light_primaryContainer

@Composable
fun ToggleButtonComponent(
    size: Dp = 45.dp,
    iconOn: ImageVector,
    iconOff: ImageVector,
    target: Boolean?
):Boolean {
    val context = LocalContext.current
    val dataStore = StoreUserPreference(context)
    val darkTheme = dataStore.getThemePreference.collectAsState(initial = false)
    var checked by remember { mutableStateOf((darkTheme.value == true)) }
//    var checked2 by remember { mutableStateOf(darkTheme.value) }
//    if (darkTheme.value == true){
//        checked = true
//    }
    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = md_theme_dark_primary,
            checkedTrackColor = md_theme_dark_primaryContainer,
            uncheckedThumbColor = md_theme_light_primary,
            uncheckedTrackColor = md_theme_light_primaryContainer,
        ),
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = iconOn,
                    contentDescription = "iconOn",
                    modifier = Modifier.size(size)
                )
            }
        } else {
            {
                Icon(
                    imageVector = iconOff,
                    contentDescription = "iconOff",
                    modifier = Modifier.size(size)
                )
            }

        }
    )
    return checked
}