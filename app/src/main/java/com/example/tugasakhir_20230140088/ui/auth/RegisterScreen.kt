package com.example.tugasakhir_20230140088.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tugasakhir_20230140088.R
import com.example.tugasakhir_20230140088.navigation.NavRoutes
import com.example.tugasakhir_20230140088.viewmodel.AuthEvent
import com.example.tugasakhir_20230140088.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val PixelFont = FontFamily(Font(R.font.pixeled))

    LaunchedEffect(uiState.isRegistered) {
        if (uiState.isRegistered) {
            viewModel.resetState()
            navController.navigate(NavRoutes.AUTH) {
                popUpTo(NavRoutes.REGISTER) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F1E8)) // Soft cream background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // HEADER WITH PIXEL AESTHETIC
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 48.dp)
            ) {
                Text(
                    text = "NOIREST",
                    fontFamily = PixelFont,
                    fontSize = 32.sp,
                    color = Color(0xFF6B5D54),
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Create Your Account",
                    fontSize = 14.sp,
                    color = Color(0xFF8B7D74)
                )
            }

            // FORM CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {

                    // EMAIL FIELD
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = {
                            Text(
                                "Email",
                                color = Color(0xFFB0A89F)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF8B7D6B),
                            unfocusedBorderColor = Color(0xFFD4CCC1),
                            focusedTextColor = Color(0xFF3D3832),
                            unfocusedTextColor = Color(0xFF3D3832),
                            cursorColor = Color(0xFF8B7D6B),
                            focusedContainerColor = Color(0xFFFAF8F5),
                            unfocusedContainerColor = Color(0xFFFAF8F5)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // PASSWORD FIELD
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = {
                            Text(
                                "Password",
                                color = Color(0xFFB0A89F)
                            )
                        },
                        singleLine = true,
                        visualTransformation = if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible },
                                enabled = password.isNotEmpty()
                            ) {
                                Icon(
                                    imageVector = if (passwordVisible)
                                        Icons.Filled.VisibilityOff
                                    else
                                        Icons.Filled.Visibility,
                                    contentDescription = null,
                                    tint = Color(0xFF8B7D6B)
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF8B7D6B),
                            unfocusedBorderColor = Color(0xFFD4CCC1),
                            focusedTextColor = Color(0xFF3D3832),
                            unfocusedTextColor = Color(0xFF3D3832),
                            cursorColor = Color(0xFF8B7D6B),
                            focusedContainerColor = Color(0xFFFAF8F5),
                            unfocusedContainerColor = Color(0xFFFAF8F5)
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // REGISTER BUTTON
                    Button(
                        onClick = {
                            viewModel.onEvent(
                                AuthEvent.Register(email, password)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(14.dp),
                        enabled = !uiState.isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6B8E6F), // Soft green
                            disabledContainerColor = Color(0xFFB0C4B1)
                        )
                    ) {
                        Text(
                            text = if (uiState.isLoading) "LOADING..." else "REGISTER",
                            color = Color.White,
                            fontFamily = PixelFont,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // LOGIN LINK
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = {
                                navController.navigate(NavRoutes.AUTH) {
                                    popUpTo(NavRoutes.REGISTER) { inclusive = true }
                                }
                            }
                        ) {
                            Text(
                                text = "Sudah punya akun? Login",
                                color = Color(0xFF8B7D6B),
                                fontSize = 13.sp
                            )
                        }
                    }

                    // ERROR MESSAGE
                    uiState.errorMessage?.let {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = it,
                            color = Color(0xFFD32F2F),
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}