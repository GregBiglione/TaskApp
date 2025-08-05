import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.taskapp.ui.theme.DisableColor
import com.example.taskapp.ui.theme.IsDoneColor
import com.example.taskapp.ui.theme.TaskCardColor
import com.example.taskapp.ui.theme.White

@Composable
fun TaskCard(title: String, isDone: Boolean, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimension.Small),
        colors = CardDefaults.cardColors(
            containerColor = TaskCardColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimension.ExtraSmall)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimension.Medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Task title --------------------------------------------------------------------------
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            // Task is done ------------------------------------------------------------------------
            Row {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "",
                        tint = if(isDone) IsDoneColor else DisableColor,
                    )
                }
                // Delete task ---------------------------------------------------------------------
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = White
                    )
                }
            }
        }
    }
}