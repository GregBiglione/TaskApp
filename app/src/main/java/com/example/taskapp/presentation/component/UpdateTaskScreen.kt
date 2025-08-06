import Dimension.Medium
import Dimension.Ten
import Dimension.buttonHeight
import Dimension.spacerHeight
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.taskapp.app.constant.Constant.Companion.UPDATE_TASK_SCREEN_BUTTON_TITLE
import com.example.taskapp.app.constant.Constant.Companion.UPDATE_TASK_SCREEN_TITLE
import com.example.taskapp.ui.theme.FontSize.buttonTextSize
import com.example.taskapp.ui.theme.TaskCardColor
import com.example.taskapp.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskScreen(
    title: String = "",
    navController: NavController,
) {
    var newTile by remember {
        mutableStateOf(title)
    }

    Scaffold(
        topBar = {
            CustomCenterAlignedTopAppBar(
                title = UPDATE_TASK_SCREEN_TITLE,
                onGoBackClick = {
                    goBack(navController)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(Medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text field --------------------------------------------------------------------------
            CustomOutlinedTextField(
                newTitle = newTile,
                onValueChange = {
                    newTile = it
                },
            )
            // Spacer ------------------------------------------------------------------------------
            CustomSpacer()
            // Button ------------------------------------------------------------------------------
            CustomButton(
                UPDATE_TASK_SCREEN_BUTTON_TITLE,
                onGoBackClick = {
                    goBack(navController)
                }
            )
        }
    }
}