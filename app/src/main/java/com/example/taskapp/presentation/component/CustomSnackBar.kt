import Dimension.Small
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.taskapp.ui.theme.White

@Composable
fun CustomSnackBar(
    message: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    imageVector: ImageVector
) {
    Snackbar(
        modifier = modifier.background(backgroundColor),
        containerColor = backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Small)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "",
                tint = White
            )
            Text(
                text = message,
                color = White
            )
        }
    }
}
