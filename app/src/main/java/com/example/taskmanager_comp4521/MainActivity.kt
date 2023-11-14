package com.example.taskmanager_comp4521

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskmanager_comp4521.component.AddTaskBtnComponent
import com.example.taskmanager_comp4521.component.HeaderComponent
import com.example.taskmanager_comp4521.component.AppOverviewComponent
import com.example.taskmanager_comp4521.component.MainPageComponent
import com.example.taskmanager_comp4521.component.TaskStructureComponent
import com.example.taskmanager_comp4521.ui.theme.TaskManager_Comp4521Theme
import kotlinx.coroutines.launch

data class Task(
    val day: String,
    val date: String,
    val month: String,
    val title: String,
    val tag: String,
    val time: String
)

val sampleTasks = listOf(
    Task("Sun", "11", "Dec", "Project","Assignment" ,"12:00"),
    Task("Mon", "30", "Nov", "Project","Exam" ,"12:30"),
    Task("Thurs", "22", "Nov", "Project","Quiz" ,"11:00"),
    Task("Tue", "15", "Oct", "Project","Exam" ,"2:00"),
    Task("Sat", "3", "Dec", "Project","Assignment" ,"7:00")
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
            TaskManager_Comp4521Theme(darkTheme = false){
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
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
                    MainPageComponent(
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

//@Composable
//fun TaskItem(task: Task, modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .background(Color.Black)
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Name",
//            style = TextStyle(fontWeight = FontWeight.Bold),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//        Text(
//            text = task.name,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//
//        Text(
//            text = "Priority",
//            style = TextStyle(fontWeight = FontWeight.Bold),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//        Text(
//            text = task.priority,
//            color = Color(0xFFE60008),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//
//        Text(
//            text = "Deadline",
//            style = TextStyle(fontWeight = FontWeight.Bold),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//        Text(
//            text = task.deadline,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(
//            text = "Progress",
//            style = TextStyle(fontWeight = FontWeight.Bold),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
//        SeekBar(progress = task.progress, modifier = Modifier.fillMaxWidth())
//    }
//}
