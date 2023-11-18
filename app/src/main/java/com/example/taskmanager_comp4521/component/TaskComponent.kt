package com.example.taskmanager_comp4521.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager_comp4521.Task
import com.example.taskmanager_comp4521.subTask

@Composable
fun ProgressComponent(
    currentProgress: Float
){
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
            .height(10.dp)
            .clip(MaterialTheme.shapes.small),
        progress = currentProgress,
        color = MaterialTheme.colorScheme.primary,
        trackColor = Color.White
    )
}
@Composable
fun TaskOverviewComponent(
    day: String,
    date: String,
    month: String,
    title: String,
    tag: String,
    time: String,
    priority: String)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.2f)
                .padding(end = 5.dp, top = 5.dp, start = 5.dp)
        ) {
            Text(
                text = day,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = date,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = month,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Divider(
            modifier = Modifier
                .width(2.dp)
                .fillMaxHeight()
            , color = Color.LightGray
        )

        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
                .padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = title,
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Image(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options",
                    modifier = Modifier.size(30.dp)
                )
            }
            Text(
                text = "#"+tag,
                modifier = Modifier.padding(bottom = 4.dp),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Black.copy(alpha = 0.5f)
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
                verticalAlignment = Alignment.Top
            ){
                val color = when (priority) {
                    "High" -> Color.Red
                    "Normal" -> Color.hsv(25f,0.41f,0.98f)
                    "Low" -> Color.Yellow
                    else -> Color.Black.copy(alpha = 0.5f) // Default color
                }
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier
                        .weight(1f)
                )
                    Text(
                        text = "Priority: ",
                        //modifier = Modifier.padding(bottom = 14.dp),
                        fontSize = 12.sp,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                    Text(
                        text = priority,
                        //modifier = Modifier.padding(bottom = 14.dp),
                        fontSize = 12.sp,
                        color = color
                    )

            }

            ProgressComponent(
                currentProgress = 0.7f
            )

        }
    }
}
@Composable
fun TaskDetailComponent(
    description: String,
    subTasks: List<subTask>
) {
    Box( modifier = Modifier
        .fillMaxWidth()
        .padding(top = 14.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        )
        {
            Text(
                text = description,
                modifier = Modifier
                    .padding(bottom = 14.dp),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            subTasks.forEach { subTask ->
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        text = subTask.title,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Checkbox(
                        checked = subTask.completed,
                        onCheckedChange = { //TODO
                        }
                    )
                }
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskStructureComponent(
    task: Task,
    titleFontWeight: FontWeight = FontWeight.Bold,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 4,
    paddingHorizontal: Dp = 16.dp,
    paddingVertical: Dp = 12.dp,
) {
    var expandedState by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                )
            )
            .clip(shape = MaterialTheme.shapes.small)
            .padding(
                start = paddingHorizontal,
                end = paddingHorizontal,
                top = paddingVertical,
                bottom = paddingVertical
            ),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
            TaskOverviewComponent(
                day = task.day,
                date = task.date,
                month = task.month,
                title = task.title,
                tag = task.tag,
                time = task.time,
                priority = task.priority
            )
            if (expandedState) {
                TaskDetailComponent(description = task.description, subTasks = task.subTasks)
            }
        }
    }
}


