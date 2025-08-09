import Dimension.Ten
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.example.taskapp.app.constant.Constant.Companion.ERROR_TOAST_MESSAGE
import com.example.taskapp.app.constant.Constant.Companion.SUCCESS_TOAST_MESSAGE
import com.example.taskapp.ui.theme.TaskCardColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomOutlinedTextField(
        newTitle: String,
        onValueChange: (String) -> Unit,
        onClick: (() -> Unit)? = null,
        onGoBackClick: (() -> Unit)? = null,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
    ) {
    var oldValue by remember {
        mutableStateOf(newTitle)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = newTitle,
        onValueChange = { value ->
            onValueChange(value)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Ten),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TaskCardColor
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Close keyboard ------------------------------------------------------------------
                keyboardController?.hide()

                coroutineScope.launch {
                    if (newTitle != oldValue && newTitle.isNotBlank()) {
                        oldValue = newTitle
                        snackbarHostState.showSnackbar(SUCCESS_TOAST_MESSAGE)
                        onClick?.invoke()
                        onGoBackClick?.invoke()
                    } else {
                        snackbarHostState.showSnackbar(ERROR_TOAST_MESSAGE)
                    }
                }
            }
        )
    )
}