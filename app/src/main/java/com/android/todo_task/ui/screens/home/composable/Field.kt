package com.android.todo_task.ui.screens.home.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.android.todo_task.utils.Required
import com.android.todo_task.utils.Validator

@ExperimentalComposeUiApi
class Field(
    val name: String,
    val label: String = "",
    val fieldText: String = "",
    val validators: List<Validator>,
    val placeholder: String,
    val imeAction: ImeAction = ImeAction.Done,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val type: FieldType = FieldType.Description
) {
    var text: String by mutableStateOf(fieldText)
    var lbl: String by mutableStateOf(label)
    var hasError: Boolean by mutableStateOf(false)

    fun clear() {
        text = ""
    }

    private fun showError(error: String) {
        hasError = true
        lbl = error
    }

    private fun hideError() {
        lbl = label
        hasError = false
    }

    @Composable
    fun Content() {
        DescriptionTextBox(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            placeholder = placeholder,
            onTextChanged = {
                text = it
                hideError()
            },
            isError = hasError,
            keyboardType = keyboardType,
            imeAction = imeAction,
            errorMessage = lbl
        )
    }

    @Composable
    fun ContentTitle() {
        TitleTextBox(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            placeholder = placeholder,
            onTextChanged = {
                text = it
                hideError()
            },
            isError = hasError,
            keyboardType = keyboardType,
            imeAction = imeAction,
            errorMessage = lbl
        )
    }

    fun validate(): Boolean {
        return validators.map {
            when (it) {
                is Required -> {
                    if (text.isEmpty()) {
                        showError(it.message)
                        return@map false
                    }
                    true
                }
            }
        }.all { it }
    }
}

enum class FieldType {
    Title,
    Description
}
