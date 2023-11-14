package com.example.taskmanager_comp4521.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

@Composable
fun ProgressComponent(
    currentProgress: Float
){
    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp ,end = 4.dp )
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
    time: String)
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
                modifier = Modifier.padding(bottom = 2.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = date,
                modifier = Modifier.padding(bottom = 2.dp),
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
            Text(
                text = time,
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = 12.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
            ProgressComponent(
                currentProgress = 0.7f
            )

        }
    }
}
@Composable
fun TaskDetailComponent() {
    
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskStructureComponent(
    task: Task,
    titleFontWeight: FontWeight = FontWeight.Bold,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 4,
    paddingHorizonal: Dp = 16.dp,
    paddingVertical: Dp = 12.dp,
) {
    var expandedState by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .clip(shape = MaterialTheme.shapes.small)
            .padding(
                start = paddingHorizonal,
                end = paddingHorizonal,
                top = paddingVertical,
                bottom = paddingVertical
            ),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    start = paddingHorizonal,
//                    end = paddingHorizonal,
//                    top = paddingVertical,
//                    bottom = paddingVertical
//                )
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
            TaskOverviewComponent(
                day = task.day,
                date = task.date,
                month = task.month,
                title = task.title,
                tag = task.tag,
                time = task.time
            )
            if (expandedState) {
                Text(
                    text = "Hi i am a place holder for the description",
                    fontWeight = descriptionFontWeight,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


//@Composable
//@Preview
//fun ExpandableCardPreview() {
//
//    TaskManager_Comp4521Theme(darkTheme = false){
//        TaskStructureComponent(
//        title = "My Title",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
//                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
//                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
//                "ullamco laboris nisi ut aliquip ex ea commodo consequat."
//        )
//    }
////    TaskOverviewComponent(
////        day = "Sun",
////        date = "15",
////        month = "Dec",
////        title = "Quiz",
////        tag ="hi i am quiz" ,
////        time = "09:30",
////        optionsIcon = Icons.Default.MoreVert
////    )
//
//}

