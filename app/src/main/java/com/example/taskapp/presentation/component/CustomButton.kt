import Dimension.Medium
import Dimension.buttonHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskapp.ui.theme.DisableColor
import com.example.taskapp.ui.theme.FontSize.buttonTextSize
import com.example.taskapp.ui.theme.TaskCardColor
import com.example.taskapp.ui.theme.White

@Composable
fun CustomButton(
        buttonTitle: String,
        onClick: (() -> Unit)? = null,
        isClickable: Boolean = true
    ) {
    Button(
        onClick = {
            onClick?.invoke()
        },
        enabled = isClickable,
        colors = ButtonDefaults.buttonColors(
            containerColor = TaskCardColor,
            contentColor = White,
            disabledContainerColor = DisableColor,
            disabledContentColor = White
        ),
        modifier = Modifier.fillMaxWidth()
            .height(buttonHeight),
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            modifier = Modifier.padding(end = Medium)
        )
        Text(
            buttonTitle,
            fontSize = buttonTextSize,
        )
    }
}