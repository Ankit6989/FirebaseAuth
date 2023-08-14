package com.apcoding.firebaseauth.ui.auth


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.apcoding.firebaseauth.R
import com.apcoding.firebaseauth.data.Resource
import com.apcoding.firebaseauth.navigation.ROUTE_HOME
import com.apcoding.firebaseauth.navigation.ROUTE_LOGIN
import com.apcoding.firebaseauth.navigation.ROUTE_SIGNUP
import com.apcoding.firebaseauth.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//This line defines a Composable function named LoginScreen, which takes two parameters: viewModel of type AuthViewModel? and navController of type NavController.
// Composable functions are used to define UI components in Jetpack Compose.
fun LoginScreen(viewModel: AuthViewModel?, navController: NavController) {
// These lines declare two mutable state variables, email and password, using the remember function. Mutable state variables are used to store data that can change over time, and the remember function ensures that the state is preserved across recompositions of the Composable.
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
//This line collects the loginFlow state flow from the provided viewModel using the collectAsState extension function. collectAsState is used to observe state flows and convert them into Composable-friendly state objects.
// The loginFlow state will reflect the progress and outcome of the login operation.
    val loginFlow = viewModel?.loginFlow?.collectAsState()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        //This line creates a set of references (anchors) for various components within a constraint layout. The createRefs() function returns an object that contains these references.
        //These are individual reference variables that correspond to different components within the constraint layout. These references will be used to specify constraints between the components, determining their positioning and alignment.
        val (refHeader, refEmail, refPassword, refButtonLogin, refTextSignup, refLoader) = createRefs()
        val spacing = MaterialTheme.spacing

        Box(
            modifier = Modifier
                //This code snippet appears to define a constraint layout using Jetpack Compose's ConstraintLayout DSL.
                // The provided code constrains a Composable referred to as refHeader within the constraints of its parent layout.
                .constrainAs(refHeader) {
                    top.linkTo(parent.top, spacing.extraLarge)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .wrapContentSize()
        ) {
            AuthHeader()
        }

        TextField(
            value = email, //The value parameter specifies the current value displayed in the input field. Here, email is the mutable state variable that holds the content of the email address.
            onValueChange = { //The onValueChange parameter is a callback that is triggered whenever the user changes the input. The lambda function { email = it } updates the email variable with the new input value (it refers to the new value).
                email = it
            },
            // The label parameter sets a label above the input field.
            // In this case, the label is created using the Text composable and the stringResource function is used to retrieve the label text from a string resource file (R.string.email).
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            modifier = Modifier.constrainAs(refEmail) {
                top.linkTo(refHeader.bottom, spacing.extraLarge)
                start.linkTo(parent.start, spacing.large)
                end.linkTo(parent.end, spacing.large)
                width = Dimension.fillToConstraints
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            visualTransformation = PasswordVisualTransformation(), //This line makes the password in coded form
            modifier = Modifier.constrainAs(refPassword) {
                top.linkTo(refEmail.bottom, spacing.medium)
                start.linkTo(parent.start, spacing.large)
                end.linkTo(parent.end, spacing.large)
                width = Dimension.fillToConstraints
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        Button(
            onClick = {
                viewModel?.login(email, password)
            },
            modifier = Modifier.constrainAs(refButtonLogin) {
                top.linkTo(refPassword.bottom, spacing.large)
                start.linkTo(parent.start, spacing.extraLarge)
                end.linkTo(parent.end, spacing.extraLarge)
                width = Dimension.fillToConstraints
            }
        ) {
            Text(text = stringResource(id = R.string.login), style = MaterialTheme.typography.titleMedium)
        }


        Text(
            modifier = Modifier
                .constrainAs(refTextSignup) {
                    top.linkTo(refButtonLogin.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.extraLarge)
                    end.linkTo(parent.end, spacing.extraLarge)
                }
                .clickable {
                    navController.navigate(ROUTE_SIGNUP) {
                        popUpTo(ROUTE_LOGIN) { inclusive = true }
                    }
                },
            text = stringResource(id = R.string.dont_have_account),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        //This block of code uses the Kotlin let function to execute a block of code if loginFlow?.value is not null.
        // This is used to safely handle the nullable value.
        loginFlow?.value?.let {
            when (it) {
                //This block of code uses the Kotlin let function to execute a block of code if loginFlow?.value is not null.
                // This is used to safely handle the nullable value.
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }

                // If the value of loginFlow is a Resource.Loading, this block is executed.
                // It displays a circular progress indicator (a loading spinner) using the CircularProgressIndicator Composable.
                // The modifier is used to define the layout constraints for positioning the indicator.
                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.constrainAs(refLoader) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }

                //If the value of loginFlow is a Resource.Success, this block is executed.
                // It uses a LaunchedEffect to perform a navigation action using the navController.navigate function.
                // It navigates to the ROUTE_HOME destination and includes a pop-up behavior to remove the ROUTE_LOGIN destination from the back stack.
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_LOGIN) { inclusive = true }
                        }
                    }
                }
            }
        }
    }
}
