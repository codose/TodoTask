package com.android.todo_task.ui.screens.home.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.android.todo_task.domain.ColorMap
import com.android.todo_task.utils.Required
import com.android.todo_task.utils.Validator

@ExperimentalComposeUiApi
class Field(
    val name: String,
    val label: String = "",
    val validators: List<Validator>,
    val placeholder: String,
    val imeAction: ImeAction = ImeAction.Done,
    val keyboardType: KeyboardType = KeyboardType.Text,
    val type: FieldType = FieldType.Description
) {
    var text: String by mutableStateOf("")
    var lbl: String by mutableStateOf(label)
    var hasError: Boolean by mutableStateOf(false)
    var colorMap: ColorMap by mutableStateOf(ColorMap.Green)
    var reset: Boolean by mutableStateOf(false)
    var update: Boolean by mutableStateOf(false)

    fun clear() {
        reset = true
        update = false
    }

    fun update(newText: String, colorMap: ColorMap = ColorMap.Green) {
        text = newText
        this.colorMap = colorMap
        update = true
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
            colorMap = colorMap,
            modifier = Modifier.fillMaxWidth(),
            placeholder = placeholder,
            onTextChanged = {
                text = it
                hideError()
            },
            isError = hasError,
            keyboardType = keyboardType,
            imeAction = imeAction,
            errorMessage = lbl,
            onColorMappedChange = {
                colorMap = it
            },
            reset = reset,
            onReset = {
                reset = false
            },
            update = update
        )
        update = false
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
            errorMessage = lbl,
            onReset = {
                reset = false
            },
            reset = reset,
            update = update
        )
        update = false
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
