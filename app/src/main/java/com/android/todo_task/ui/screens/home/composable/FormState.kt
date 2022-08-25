package com.android.todo_task.ui.screens.home.composable

import androidx.compose.ui.ExperimentalComposeUiApi
import com.android.todo_task.domain.ColorMap

@ExperimentalComposeUiApi
class FormState {
    var fields: List<Field> = listOf()

    fun validate(): Boolean {
        var valid = true
        for (field in fields) if (!field.validate()) {
            valid = false
            break
        }
        return valid
    }

    fun getData(): Map<String, Any> = fields.associate {
        if (it.type == FieldType.Description) {
            it.name to Pair(it.text, it.colorMap)
        } else {
            it.name to it.text
        }
    }

    fun clear() = fields.forEach { it.clear() }

    fun update(list: List<Triple<FieldType, String, ColorMap>>) = list.forEach { item ->
        fields.first {
            it.type == item.first
        }.update(item.second, item.third)
    }
}
