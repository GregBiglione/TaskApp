import Dimension.spacerHeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomSpacer() {
    Spacer(
        modifier = Modifier.height(spacerHeight)
    )
}