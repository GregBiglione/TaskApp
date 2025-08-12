import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.taskapp.ui.theme.DarkTaskColor
import com.example.taskapp.ui.theme.TaskCardColor
import com.example.taskapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCenterAlignedTopAppBar(
        title: String,
        onGoBackClick: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DarkTaskColor,
        ),
        title = {
            Text(
                title,
                color = White,
            )
        },
        navigationIcon = {
            if(onGoBackClick != null) {
                IconButton(
                    onClick = {
                        onGoBackClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        tint = White,
                    )
                }
            }
        },
    )
}