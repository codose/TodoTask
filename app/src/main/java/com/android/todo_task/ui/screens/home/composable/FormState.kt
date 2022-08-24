package com.android.todo_task.ui.screens.home.composable

import androidx.compose.ui.ExperimentalComposeUiApi

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

    fun getData(): Map<String, String> = fields.associate { it.name to it.text }
}
