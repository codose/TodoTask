package com.android.todo_task.ui.screens.home

import androidx.compose.ui.graphics.Color
import com.android.todo_task.domain.ColorMap
import com.android.todo_task.ui.theme.LightPink
import com.android.todo_task.ui.theme.PrimaryColor

object ColorMapUtils {
    fun getColorForMap(colorMap: ColorMap): Color {
        return when (colorMap) {
            ColorMap.Purple -> Color(0xFFCAB8FD)
            ColorMap.Pink -> LightPink
            ColorMap.Green -> PrimaryColor
            ColorMap.Gray -> Color(0xFFD9D9D9)
        }
    }
}
