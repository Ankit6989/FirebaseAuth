package com.apcoding.firebaseauth.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.apcoding.firebaseauth.data.utils.await
import javax.inject.Inject

//This class is designed to be used with dependency injection frameworks like Dagger.
// The @Inject annotation on the constructor indicates that instances of AuthRepositoryImpl can be created and provided by the dependency injection framework.
class AuthRepositoryImpl @Inject constructor(

    //his is a private property of type FirebaseAuth. The FirebaseAuth class is provided by the Firebase Authentication library and allows you to interact with Firebase authentication services.
    private val firebaseAuth: FirebaseAuth

    //This line signifies that AuthRepositoryImpl implements the AuthRepository interface.
// The colon (:) followed by AuthRepository indicates that AuthRepositoryImpl inherits the structure and contract defined by the AuthRepository interface.
) : AuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            //This part accesses the user property of the result object.
            // The double exclamation mark (!!) is the non-null assertion operator, which asserts that result.user is not null.
            // This is used when you are confident that result.user is guaranteed to be non-null at this point, and if it's unexpectedly null, a NullPointerException will be thrown.
            Resource.Success(result.user!!)
        } catch (e: Exception) { //This line starts the catch block, which is executed if an exception occurs during the authentication process.
            e.printStackTrace() //Within the catch block, this line prints the stack trace of the caught exception. Printing the stack trace helps in identifying where and why the exception occurred, aiding in debugging.
            Resource.Failure(e) //Following the stack trace printing, this line creates a Resource.Failure object. It encapsulates the caught exception, indicating that the authentication operation has failed. The Resource.Failure object can be used to handle and communicate the failure in a structured way.
        }
    }

    override suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()

            //This line uses the updateProfile() method of the FirebaseUser class to update the display name of the user.
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}