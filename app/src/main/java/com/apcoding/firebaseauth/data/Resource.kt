package com.apcoding.firebaseauth.data

import java.lang.Exception

sealed class Resource<out R> {

    //the Success data class within the Resource sealed class represents a successful outcome of an operation.
    // It holds a result value of type R and is designed to be used as part of the broader Resource pattern to encapsulate operation outcomes in a structured manner, especially when dealing with asynchronous operations.
    data class Success<out R>(val result: R): Resource<R>()

    //the Failure data class within the Resource sealed class represents a failure outcome of an operation.
    // It holds an exception that caused the failure and is designed to be used as part of the broader Resource pattern to encapsulate operation outcomes in a structured manner, especially when dealing with asynchronous operations.
    data class Failure(val exception: Exception): Resource<Nothing>()

    //the Loading object within the Resource sealed class represents the state of an operation in progress.
    // It's designed to be used as part of the broader Resource pattern to encapsulate operation outcomes in a structured manner, especially when dealing with asynchronous operations.
    // This object is useful to indicate to the user or application that some operation is currently ongoing.
    object Loading: Resource<Nothing>()
}