package com.example.taskmanager_comp4521

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.taskmanager_comp4521.component.Navigation
import com.example.taskmanager_comp4521.ui.theme.TaskManager_Comp4521Theme
import kotlinx.coroutines.launch

data class subTask(
    val title: String,
    val completed: Boolean
)
data class Task(
    val day: String,
    val date: String,
    val month: String,
    val title: String,
    val description: String,
    val tag: String,
    val time: String,
    val priority: String,
    val subTasks: List<subTask>
)

val sampleSubTasks = listOf(
    subTask("review all chapters", true),
    subTask("finish past paper", false)
)
val sampleTasks = listOf(
    Task("Sun", "11", "Dec", "Comp 4521Project","Comp4521 final project, a task manager app that can help user manage their tasks effectively","Assignment" ,"12:00", "High", sampleSubTasks),
    Task("Mon", "30", "Nov", "Comp2211 Midterm","Comp4521 final project, a task manager app that can help user manage their tasks effectively","Exam" ,"12:30","Normal",sampleSubTasks),
    Task("Thurs", "22", "Nov", "Lang4030 Project","Comp4521 final project, a task manager app that can help user manage their tasks effectively","Quiz" ,"11:00","Low",sampleSubTasks),
    Task("Tue", "15", "Oct", "Exam","Comp4521 final project, a task manager app that can help user manage their tasks effectively","Exam" ,"2:00","High",sampleSubTasks),
    Task("Sat", "3", "Dec", "Project","Comp4521 final project, a task manager app that can help user manage their tasks effectively","Assignment" ,"7:00","N/A",sampleSubTasks)
)

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

val items = listOf(
    NavigationItem(
        title = "All Tasks",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        badgeCount = 8
    ),
    NavigationItem(
        title = "Assignment",
        selectedIcon = Icons.Filled.Info,
        unselectedIcon = Icons.Outlined.Info,
        badgeCount = 3
    ),
    NavigationItem(
        title = "Quiz",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        badgeCount = 5
    ),
)
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManager_Comp4521Theme(darkTheme = false
            ){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    var selectedItemIndex by rememberSaveable {
                        mutableStateOf(0)
                    }
                    ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet {
                                Spacer(modifier = Modifier.height(16.dp))
                                items.forEachIndexed { index, item ->
                                    NavigationDrawerItem(
                                        label = {
                                            Text(text = item.title)
                                        },
                                        selected = index == selectedItemIndex,
                                        onClick = {
//                                            navController.navigate(item.route)
                                            selectedItemIndex = index
                                            scope.launch {
                                                drawerState.close()
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (index == selectedItemIndex) {
                                                    item.selectedIcon
                                                } else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        },
                                        badge = {
                                            item.badgeCount?.let {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        },
                                        modifier = Modifier
                                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                                    )
                                }
                            }
                        },
                        drawerState = drawerState
                    ) {
                        Navigation(
                            sampleTasks,
                            {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

