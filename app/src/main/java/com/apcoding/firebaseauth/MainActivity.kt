package com.apcoding.firebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.apcoding.firebaseauth.navigation.AppNavHost
import com.apcoding.firebaseauth.ui.auth.AuthViewModel
import com.apcoding.firebaseauth.ui.theme.FirebaseAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseAuthTheme {
                AppNavHost(viewModel)
            }
        }
    }
}