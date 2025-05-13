package com.savenko.mui8.presentation

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoanInputField(currentValue : Float, onValueChange : (Float) -> Unit){
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester.Default
    val controller = LocalSoftwareKeyboardController.current
    BasicTextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .width(IntrinsicSize.Min)
            .padding(start = 12.dp),
        textStyle = MaterialTheme.typography.titleMedium.copy(
            fontSize = 48.sp,
            letterSpacing = 1.3.sp,
            color = MaterialTheme.colorScheme.primary
        ),
        value = currentValue.toString(),
        onValueChange = { newText: String ->
            val result = try {
                val convertedValue = newText.toFloat()
                if (convertedValue < Float.MAX_VALUE) {
                    convertedValue
                } else {
                   Float.MAX_VALUE
                }
            } catch (e: NumberFormatException) {
                currentValue
            }
            onValueChange(result)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                controller?.hide()
                focusManager.clearFocus()
            }
        ),
        maxLines = 1,
    )
}