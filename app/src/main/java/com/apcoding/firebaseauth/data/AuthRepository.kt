package com.apcoding.firebaseauth.data

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    //suspend fun is used to define functions that can be safely called from a coroutine.
    // These functions can perform asynchronous operations without blocking the main (UI) thread. When a function is marked as suspend, it indicates that the function can "suspend" the execution of the calling coroutine until the asynchronous operation is complete. This allows for smooth and efficient handling of asynchronous tasks.
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>

    //fun is used to define regular functions that don't involve coroutines or asynchronous operations.
    // These functions are executed sequentially and can potentially block the main thread if they perform time-consuming operations.
    fun logout()
}