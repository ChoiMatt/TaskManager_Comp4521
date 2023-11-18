package com.example.taskmanager_comp4521.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//@Composable
//fun ToggleButtonComponent(
//    darkTheme: Boolean = false,
//    size: Dp = 30.dp,
//    iconSize: Dp = size / 3,
//    padding: Dp = 10.dp,
//    borderWidth: Dp = 1.dp,
//    parentShape: Shape = CircleShape,
//    toggleShape: Shape = CircleShape,
//    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
//    onClick: () -> Unit
//) {
//    val offset by animateDpAsState(
//        targetValue = if (darkTheme) 0.dp else size,
//        animationSpec = animationSpec
//    )
//
//    Box(modifier = Modifier
//        .width(size * 2)
//        .height(size)
//        .clip(shape = parentShape)
//        .clickable { onClick() }
//        .background(MaterialTheme.colorScheme.secondaryContainer)
//    ) {
//        Box(
//            modifier = Modifier
//                .size(size)
//                .offset(x = offset)
//                .padding(all = padding)
//                .clip(shape = toggleShape)
//                .background(MaterialTheme.colorScheme.primary)
//        ) {}
//        Row(
//            modifier = Modifier
//                .border(
//                    border = BorderStroke(
//                        width = borderWidth,
//                        color = MaterialTheme.colorScheme.primary
//                    ),
//                    shape = parentShape
//                )
//        ) {
//            Box(
//                modifier = Modifier.size(size),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    modifier = Modifier.size(iconSize),
//                    imageVector = Icons.Default.,
//                    contentDescription = "Theme Icon",
//                    tint = if (darkTheme) MaterialTheme.colorScheme.secondaryContainer
//                    else MaterialTheme.colorScheme.primary
//                )
//            }
//            Box(
//                modifier = Modifier.size(size),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    modifier = Modifier.size(iconSize),
//                    imageVector = Icons.Default.LightMode,
//                    contentDescription = "Theme Icon",
//                    tint = if (darkTheme) MaterialTheme.colorScheme.primary
//                    else MaterialTheme.colorScheme.secondaryContainer
//                )
//            }
//        }
//    }
//}

@Composable
fun ToggleButtonComponent(
    size: Dp = 30.dp,
    iconOn: ImageVector,
    iconOff: ImageVector
) {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        thumbContent = if (checked) {
            {
                Icon(
                    imageVector = iconOn,
                    contentDescription = null,
                    modifier = Modifier.size(size),
                )
            }
        } else {
            {
                Icon(
                    imageVector = iconOff,
                    contentDescription = null,
                    modifier = Modifier.size(size),
                )
            }

        }
    )
}