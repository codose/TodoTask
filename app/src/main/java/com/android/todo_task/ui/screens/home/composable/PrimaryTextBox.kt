package com.android.todo_task.ui.screens.home.composable

import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.android.todo_task.domain.ColorMap
import com.android.todo_task.ui.screens.home.ColorMapUtils
import com.android.todo_task.ui.theme.LightPink
import com.android.todo_task.ui.theme.PrimaryColor
import com.android.todo_task.ui.theme.TextColor
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.color.KalendarThemeColor
import com.himanshoe.kalendar.model.KalendarType
import java.util.*

@ExperimentalComposeUiApi
@Composable
fun DescriptionTextBox(
    modifier: Modifier,
    value: String = "",
    placeholder: String,
    imeAction: ImeAction = ImeAction.Done,
    colorMap: ColorMap,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChanged: (String) -> Unit,
    onKeyboardAction: ((String) -> Unit)? = null,
    onColorMappedChange: (ColorMap) -> Unit,
    isError: Boolean = false,
    errorMessage: String = "",
    reset: Boolean = true,
    update: Boolean = true,
    onReset: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var showPicker by remember {
        mutableStateOf(false)
    }
    var text by remember {
        mutableStateOf(value)
    }
    var backgroundColor by remember {
        mutableStateOf(ColorMapUtils.getColorForMap(colorMap))
    }

    SideEffect {
        if (reset) {
            text = ""
            backgroundColor = PrimaryColor
            onReset.invoke()
        }
        if (update) {
            text = value
        }
        onTextChanged(text)
    }
    Column {
        OutlinedTextField(
            shape = RoundedCornerShape(12.dp),
            singleLine = false,
            value = text,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                backgroundColor = backgroundColor,
                textColor = TextColor
            ),
            isError = isError,
            modifier = modifier.height(180.dp),
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Black.copy(alpha = 0.5f),
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            },
            onValueChange = {
                onTextChanged(it)
                text = it
            },
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            keyboardActions = KeyboardActions(
                onAny = {
                    keyboardController?.hide()
                    onKeyboardAction?.invoke(value)
                }
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(shape = RoundedCornerShape(12.dp), color = backgroundColor),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomIconButton(Icons.Outlined.Image) {
                }
                CustomIconButton(Icons.Outlined.Notifications) {
                }
                CustomIconButton(Icons.Outlined.Palette) {
                    showPicker = true
                }
                CustomIconButton(Icons.Outlined.Download) {
                }
                Spacer(modifier = Modifier.weight(1f))
                CustomIconButton(Icons.Outlined.Undo) {
                }
                CustomIconButton(Icons.Outlined.Redo) {
                }
                Spacer(modifier = Modifier.width(8.dp))
                CustomIconButton(Icons.TwoTone.Menu) {
                }
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        if (showPicker) {
            Dialog(onDismissRequest = { showPicker = false }) {
                ColorPicker {
                    backgroundColor = ColorMapUtils.getColorForMap(it)
                    onColorMappedChange.invoke(it)
                    showPicker = false
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun TitleTextBox(
    modifier: Modifier,
    value: String = "",
    placeholder: String,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Number,
    onTextChanged: (String) -> Unit,
    onKeyboardAction: ((String) -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String = "",
    reset: Boolean = true,
    update: Boolean = false,
    onReset: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember {
        mutableStateOf(value)
    }
    SideEffect {
        if (reset) {
            text = ""
            onReset.invoke()
        }
        if (update) {
            text = value
        }
        onTextChanged(text)
    }
    Column {
        OutlinedTextField(
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            value = text,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                textColor = TextColor
            ),
            isError = isError,
            modifier = modifier,
            onValueChange = {
                text = it
                onTextChanged(it)
            },
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            keyboardActions = KeyboardActions(
                onAny = {
                    keyboardController?.hide()
                    onKeyboardAction?.invoke(value)
                }
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Start
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    fontSize = 24.sp,
                    color = TextColor.copy(alpha = 0.5f),
                    textAlign = TextAlign.Start
                )
            }
        )
        Spacer(modifier = Modifier.height(2.dp))
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    onDayChanged: (String) -> Unit,
    onAllDaySelected: (Boolean) -> Unit
) {
    var text by remember { mutableStateOf("Select a day") }
    var dialogShow by remember {
        mutableStateOf(false)
    }
    var isChecked by remember {
        mutableStateOf(false)
    }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Default.Timer, contentDescription = null)
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = text, fontSize = 14.sp,
            modifier = Modifier.clickable {
                dialogShow = true
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "All Day", fontSize = 16.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    onAllDaySelected.invoke(it)
                    isChecked = it
                }
            )
        }
    }
    if (dialogShow) {
        Dialog(
            onDismissRequest = {
                dialogShow = false
            },
            properties = DialogProperties()
        ) {
            DatePicker(onDateSelected = { date ->
                text = date.toString()
                dialogShow = false
            }) {
                dialogShow = false
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun TimeSelector(
    modifier: Modifier = Modifier,
    onRangeSelected: (String, String) -> Unit
) {
    var text by remember { mutableStateOf("--:--") }
    var isChecked by remember {
        mutableStateOf(false)
    }
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val context = LocalContext.current
    // Creating a TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        context,
        { _, mHour: Int, mMinute: Int ->
            text = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Default.Timer, contentDescription = null)
        Spacer(modifier = Modifier.width(24.dp))
        TimeSelector()
        Spacer(modifier = Modifier.width(8.dp))
        Divider(
            Modifier
                .height(3.dp)
                .width(12.dp),
            color = TextColor
        )
        Spacer(modifier = Modifier.width(8.dp))
        TimeSelector()
        Spacer(modifier = Modifier.weight(1f))
        BorderedText(value = "1:00hrs", backgroundColor = LightPink)
    }
}

@Composable
fun TimeSelector() {
    var text by remember { mutableStateOf("--:--") }
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]
    val context = LocalContext.current
    // Creating a TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        context,
        { _, mHour: Int, mMinute: Int ->
            var minute = "$mMinute"
            if (mMinute < 10) {
                minute = "0$minute"
            }
            text = "$mHour:$minute"
        }, mHour, mMinute, false
    )
    BorderedText(value = text) {
        mTimePickerDialog.show()
    }

}

@Composable
fun BorderedText(
    value: String,
    backgroundColor: Color = Color.Transparent,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .clickable { onClick?.invoke() }
            .border(border = BorderStroke(2.dp, TextColor), shape = RoundedCornerShape(8.dp))
            .defaultMinSize(minWidth = 56.dp)
    ) {
        Text(text = value, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
    }
}

@Composable
fun ItemSelector(values: List<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (values.isNotEmpty()) values.first() else "No Items",
            color = TextColor
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            modifier = Modifier.rotate(90.0f),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun TextBox() {
    Box(modifier = Modifier.background(Color.White)) {
        ItemSelector(values = listOf())
    }
}
