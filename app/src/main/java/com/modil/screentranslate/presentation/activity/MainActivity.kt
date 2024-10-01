package com.modil.screentranslate.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.modil.screentranslate.presentation.ui.theme.ScreenTranslateTheme
import com.modil.screentranslate.presentation.utils.PermissionHandler

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val permissionHandler = PermissionHandler(this@MainActivity)
        val overlayPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (permissionHandler.isOverlayPermissionGranted()) {
                    Log.d("MainActivity", "Permission granted")
                } else {
                    Log.d("MainActivity", "Permission not Granted")
                }
            }



        setContent {
            ScreenTranslateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onStartButtonClick = {
                            permissionHandler.requestOverlayPermission(
                                overlayPermissionLauncher
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onStartButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Button(onClick = { onStartButtonClick() }) {
            Text(text = "Старт")
        }
    }
}

