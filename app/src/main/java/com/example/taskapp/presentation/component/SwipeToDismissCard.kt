import Dimension.Ten
import Dimension.taskCardHeight
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import com.example.taskapp.ui.theme.ErrorColor
import com.example.taskapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SwipeToDismissCard(
    modifier: Modifier = Modifier,
    onSwipe: () -> Unit,
    content: @Composable () -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if(it == DismissValue.DismissedToStart) {
                onSwipe()
                true
            }
            else {
                false
            }
        }
    )
    val isDismissInProgress = dismissState.targetValue == DismissValue.DismissedToStart ||
            dismissState.progress.fraction > 0.9f
    val isVisible = dismissState.currentValue != DismissValue.DismissedToStart
    var contentHeight by remember { mutableStateOf(0) } // 1
    val density = LocalDensity.current

    // Animation on swipe --------------------------------------------------------------------------
    AnimatedVisibility(
        visible = isVisible,
    ) {
        SwipeToDismiss(
            state = dismissState,
            directions = setOf(
                DismissDirection.EndToStart
            ),
            modifier = Modifier.height(taskCardHeight),
            background = {
                if(dismissState.dismissDirection != null && isDismissInProgress) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                with((density)) {
                                    contentHeight.toDp()
                                }
                            )
                            .background(ErrorColor),

                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            modifier = Modifier.padding(
                                end = Ten
                            ),
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = White
                        )
                    }
                }
            },
            dismissContent = {
                Box(
                    modifier = modifier
                        .onGloballyPositioned { coordinates ->
                            contentHeight = coordinates.size.height //2
                        }
                ) {
                    content()
                }
            }
        )
    }
}