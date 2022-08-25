package com.android.todo_task.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.todo_task.ui.screens.home.composable.*
import com.android.todo_task.ui.theme.TextColor
import com.android.todo_task.ui.viewmodel.HomeViewModel
import com.android.todo_task.utils.Required

private const val TITLE_FIELD = "TITLE_FIELD"
private const val DESCRIPTION_FIELD = "DESCRIPTION_FIELD"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
    val taskLists by viewModel.todos.collectAsState()
    var allDaySelected by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        TopAppBar(title = "Task Tracker")
        val formState by remember { mutableStateOf(FormState()) }
        Form(
            state = formState,
            fields = listOf(
                Field(
                    name = TITLE_FIELD, validators = listOf(Required()),
                    label = "",
                    placeholder = "Title",
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                    type = FieldType.Title
                ),
                Field(
                    name = DESCRIPTION_FIELD, validators = listOf(Required()),
                    label = "",
                    placeholder = "Task Description",
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                    type = FieldType.Description
                )
            )
        )
        DaySelector(
            onDayChanged = {},
            onAllDaySelected = {
                allDaySelected = it
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(Modifier.padding(start = 0.dp)) {
            ItemSelector(values = listOf("Does not repeat"))
        }
        Spacer(modifier = Modifier.height(8.dp))
        AnimatedVisibility(visible = allDaySelected.not()) {
            TimeSelector { start, end ->
            }
        }
        TextButton(
            onClick = {
                if (formState.validate()) {
                    val data = formState.getData()
                    val title = formState.getData()[TITLE_FIELD].orEmpty()
                    val description = formState.getData()[DESCRIPTION_FIELD].orEmpty()
                    viewModel.insertTask(title, description)
                    formState.clear()
                }
            }
        ) {
            Text(text = "Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Task Created", color = TextColor, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Icon(imageVector = Icons.Outlined.Sort, contentDescription = null)
        }

        TasksList(
            Modifier.fillMaxWidth(), taskLists,
            onDelete = {
                viewModel.deleteTodo(it)
            }
        ) {
        }
    }
}
