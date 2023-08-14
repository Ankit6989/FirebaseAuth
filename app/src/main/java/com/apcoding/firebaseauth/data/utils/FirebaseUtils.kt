package com.apcoding.firebaseauth.data.utils

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException


@OptIn(ExperimentalCoroutinesApi::class)
//Task<T> class, which is commonly used in Android development for handling asynchronous tasks, especially those related to Firebase operations.
// The purpose of this extension function is to provide a way to convert a Task<T> into a suspending function that can be used with Kotlin's coroutine structure.
// This is done by using the suspendCancellableCoroutine function, which is a coroutine builder that allows us to create a coroutine that can be cancelled at any time.

//This declares a suspend extension function named await for the Task<T> class.
// The function takes no parameters and returns a value of type T.
// The purpose of this function is to convert the Firebase Task<T> into a suspending function that can be awaited within a coroutine.
suspend fun <T> Task<T>.await(): T {

//This is a suspending function provided by Kotlin's coroutine library.
// It's used to create a new coroutine that's cancellable, and it takes a lambda function as an argument.

    //This lambda function takes a single parameter named cont, which is a continuation object.
    // The continuation is used to control the flow of the coroutine and to provide the result or exception once the asynchronous operation is complete.
    return suspendCancellableCoroutine { cont ->

        //This is a method provided by Firebase's Task<T> class. It registers a listener that will be called when the task is complete, whether it succeeds or fails. The listener is specified as a lambda function.
        addOnCompleteListener {
            if(it.exception != null){
                cont.resumeWithException(it.exception!!)
            }else{
                cont.resume(it.result, null)
            }
        }
    }
}