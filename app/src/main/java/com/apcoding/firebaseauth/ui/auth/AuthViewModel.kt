package com.apcoding.firebaseauth.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.apcoding.firebaseauth.data.AuthRepository
import com.apcoding.firebaseauth.data.Resource
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor( //The @Inject annotation on the constructor indicates that instances of AuthViewModel can be provided and injected using a dependency injection framework (like Dagger). The constructor keyword marks the start of the primary constructor.
    //This line declares a private property named repository of type AuthRepository. It's used to hold a reference to an instance of the AuthRepository interface, which likely provides methods for user authentication operations.
    private val repository: AuthRepository
    //This part of the declaration indicates that AuthViewModel inherits from the ViewModel class provided by Android's ViewModel architecture. This means that AuthViewModel can leverage ViewModel lifecycle management, data persistence across configuration changes, and more.
) : ViewModel() {


    //This line declares a private property _loginFlow of type MutableStateFlow<Resource<FirebaseUser>?>.
    // This mutable state flow holds information about the login process. It's initialized with null, indicating that initially, there's no active login process.
    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)

    //This line declares a public property loginFlow of type StateFlow<Resource<FirebaseUser>?>.
    // It's a read-only version of the _loginFlow property, exposed for other parts of the code to observe changes in the login process.
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    //This code defines a read-only computed property named currentUser within the AuthViewModel class.
    // This property provides access to the currently authenticated user using Firebase Authentication.
    val currentUser: FirebaseUser? //This line declares a read-only property named currentUser of type FirebaseUser?. It holds a reference to the currently authenticated user in the Firebase Authentication system. The question mark (?) indicates that the property can hold a FirebaseUser instance or be null if no user is currently authenticated.
        get() = repository.currentUser //This is a custom getter for the currentUser property. When the currentUser property is accessed, this getter method is executed. It retrieves the currentUser value from the injected repository, which is an instance of AuthRepository. The AuthRepository likely has a property or method named currentUser that provides the currently authenticated user.


    init {
        //This condition checks whether there is a currently authenticated user.
        // If the currentUser property of the injected repository is not null, it means that a user is already authenticated.
        if(repository.currentUser != null){
            //This line sets the value of the _loginFlow state flow to indicate a successful login outcome. It creates a Resource.Success instance using the currently authenticated user from the repository, and then assigns it to the _loginFlow state flow.
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }
//This is a public function named login. It takes two parameters: email and password, which are the user's login credentials.
// The function uses viewModelScope.launch to start a coroutine in the ViewModel's scope, allowing it to perform asynchronous operations without leaking resources.
    fun login(email: String, password: String) = viewModelScope.launch {
    //This line updates the _loginFlow state flow to indicate that the login process is in progress.
    // It sets the value to a Resource.Loading instance, which represents the loading state.
        _loginFlow.value = Resource.Loading
    //This line calls the login method on the injected repository, passing in the email and password parameters.
        val result = repository.login(email, password)
    //This line updates the _loginFlow state flow to indicate the outcome of the login process.
        _loginFlow.value = result
    }

    fun signup(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = repository.signup(name, email, password)
        _signupFlow.value = result
    }

    fun logout() {
        //This line calls the logout method on the injected repository.
        repository.logout()
        //This line sets the value of the _loginFlow and _signupFlow state flows to null, indicating that there's no active login or signup process.
        _loginFlow.value = null
        _signupFlow.value = null
    }
}