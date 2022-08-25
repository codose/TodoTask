package com.android.todo_task.ui.screens.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

@ExperimentalComposeUiApi
@Composable
fun Form(state: FormState, fields: List<Field>) {
    state.fields = fields

    Column() {
        fields.forEach {
            when (it.type) {
                FieldType.Description -> it.Content()
                FieldType.Title -> it.ContentTitle()
            }
        }
    }
}
