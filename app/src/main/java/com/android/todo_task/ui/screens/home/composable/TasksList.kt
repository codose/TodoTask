package com.android.todo_task.ui.screens.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.todo_task.domain.ColorMap
import com.android.todo_task.domain.TaskStatus
import com.android.todo_task.domain.TodoModel
import com.android.todo_task.ui.screens.home.ColorMapUtils
import com.android.todo_task.ui.theme.TextColor
import org.joda.time.DateTime

@Composable
fun TasksList(
    modifier: Modifier,
    taskLists: List<TodoModel>,
    onDelete: (TodoModel) -> Unit,
    onEdit: (TodoModel) -> Unit,
    onChecked: (TodoModel, Boolean) -> Unit

) {
    LazyColumn {
        items(taskLists) { todoModel ->
            TaskListItem(todoModel, onDelete, onEdit, onChecked)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun TaskListItem(
    todoModel: TodoModel,
    onDelete: (TodoModel) -> Unit,
    onEdit: (TodoModel) -> Unit,
    onChecked: (TodoModel, Boolean) -> Unit
) {
    Box(
        modifier = Modifier.background(
            ColorMapUtils.getColorForMap(todoModel.colorMap),
            shape = RoundedCornerShape(12.dp)
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var isChecked by remember {
                mutableStateOf(false)
            }
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    onChecked(todoModel, it)
                    isChecked = it
                }
            )
            Text(
                text = todoModel.title,
                color = TextColor,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomIconButton(icon = Icons.Outlined.Edit) {
                onEdit(todoModel)
            }
            CustomIconButton(icon = Icons.Outlined.Delete) {
                onDelete(todoModel)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCompose() {
    TaskListItem(
        todoModel = TodoModel(
            id = 0,
            title = "This is a test task ",
            description = "",
            validFrom = DateTime.now(),
            validTo = DateTime.now(),
            createdAt = DateTime.now(),
            updatedAt = DateTime.now(),
            status = TaskStatus.Active,
            colorMap = ColorMap.Green
        ),
        onDelete = { },
        onEdit = { },
        onChecked = { _, _ -> }
    )
}
