package com.android.todo_task.ui.screens.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.android.todo_task.domain.ColorMap
import com.android.todo_task.ui.screens.home.ColorMapUtils

@Composable
fun ColorPicker(onColorSelect: (ColorMap) -> Unit) {
    Box(modifier = Modifier.wrapContentHeight().background(Color.White)) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .width(250.dp)
        ) {
            for (color in ColorMap.values()) {
                Box(
                    Modifier
                        .background(
                            ColorMapUtils.getColorForMap(color),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            onColorSelect(color)
                        }
                        .fillMaxWidth()
                        .height(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
