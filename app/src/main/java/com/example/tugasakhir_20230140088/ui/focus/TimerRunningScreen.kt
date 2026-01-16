package com.example.tugasakhir_20230140088.ui.focus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tugasakhir_20230140088.viewmodel.FocusViewModel

@Composable
fun TimerRunningScreen(
    viewModel: FocusViewModel,
    navController: NavController
) {
    var showGiveUpDialog by remember { mutableStateOf(false) }
    var showCompletionDialog by remember { mutableStateOf(false) }

    val remainingSeconds by viewModel.remainingSeconds.collectAsState()
    val isMuted by viewModel.isMuted.collectAsState()
    val ambience by viewModel.currentAmbience.collectAsState()
    val isTimerComplete by viewModel.isTimerComplete.collectAsState()
    val completedMinutes by viewModel.completedMinutes.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(isTimerComplete) {
        if (isTimerComplete) showCompletionDialog = true
    }

    DisposableEffect(Unit) {
        viewModel.playAmbience(context)
        onDispose { viewModel.stopAmbience() }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        /* ===== SOUND TOGGLE ===== */
        IconButton(
            onClick = { viewModel.toggleMute(context) },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
                .size(48.dp)
        ) {
            Icon(
                imageVector = if (isMuted)
                    Icons.AutoMirrored.Filled.VolumeOff
                else
                    Icons.AutoMirrored.Filled.VolumeUp,
                contentDescription = "Toggle Sound",
                modifier = Modifier.size(28.dp),
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }

        /* ===== MAIN CONTENT ===== */
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Stay focused!",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.2.sp,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Timer Display
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Box(
                    modifier = Modifier.padding(horizontal = 40.dp, vertical = 32.dp)
                ) {
                    Text(
                        text = formatTime(remainingSeconds),
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 4.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            Spacer(modifier = Modifier.height(64.dp))

            /* ===== GIVE UP BUTTON ===== */
            OutlinedButton(
                onClick = { showGiveUpDialog = true },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .height(48.dp)
                    .widthIn(min = 140.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.5.dp
                )
            ) {
                Text(
                    text = "Give Up",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            /* ===== AMBIENCE STATUS ===== */
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Ambience",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$ambience playing…",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        /* ===== GIVE UP DIALOG ===== */
        if (showGiveUpDialog) {
            AlertDialog(
                onDismissRequest = { showGiveUpDialog = false },
                title = {
                    Text(
                        "Give up focus session?",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                text = {
                    Text(
                        "Your current focus session will be ended and won't be saved to statistics.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showGiveUpDialog = false
                            viewModel.giveUp(userId = 1)
                            navController.popBackStack()
                        }
                    ) {
                        Text(
                            "Give Up",
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showGiveUpDialog = false }) {
                        Text("Cancel")
                    }
                },
                shape = RoundedCornerShape(20.dp)
            )
        }

        /* ===== COMPLETION DIALOG ===== */
        if (showCompletionDialog) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(
                        "🎉 Focus Complete!",
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "You completed $completedMinutes minutes of focused work.",
                            fontSize = 15.sp
                        )
                        Text(
                            "Your progress has been saved.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showCompletionDialog = false
                            viewModel.saveCompletedSession(userId = 1)
                            navController.popBackStack()
                        },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Done",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                },
                shape = RoundedCornerShape(20.dp)
            )
        }
    }
}

private fun formatTime(seconds: Int): String {
    val m = seconds / 60
    val s = seconds % 60
    return "%02d:%02d".format(m, s)
}