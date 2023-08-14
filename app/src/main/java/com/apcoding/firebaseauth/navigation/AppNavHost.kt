package com.apcoding.firebaseauth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apcoding.firebaseauth.ui.auth.AuthViewModel
import com.apcoding.firebaseauth.ui.auth.LoginScreen
import com.apcoding.firebaseauth.ui.auth.SignupScreen
import com.apcoding.firebaseauth.ui.home.HomeScreen


@Composable
fun AppNavHost(
    viewModel: AuthViewModel, // The AuthViewModel is passed in as a parameter to the AppNavHost composable.
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(), // The NavHostController manages app navigation within a Compose app.
    startDestination: String = ROUTE_LOGIN // The default start destination is the login route.
) {
    NavHost(
        modifier = modifier, // Modifier is an interface that can be used to add styling and behavior to Compose UI elements.
        navController = navController, // The NavHostController manages app navigation within a Compose app.
        startDestination = startDestination // Specifies the initial destination when the navigation host is created.
    ) {

        //This defines a composable screen for the login route. It associates the LoginScreen composable with the login route, passing in the viewModel and navController.
        composable(ROUTE_LOGIN) {
            LoginScreen(viewModel, navController)
        }
        //Similarly, this defines a composable screen for the signup route, associating the SignupScreen composable with the signup route.
        composable(ROUTE_SIGNUP) {
            SignupScreen(viewModel, navController)
        }
        // Similarly, this defines a composable screen for the home route, associating the HomeScreen composable with the home route.
        composable(ROUTE_HOME) {
            HomeScreen(viewModel, navController)
        }
    }
}