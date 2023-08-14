package com.apcoding.firebaseauth

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
// Hilt requires you to annotate an Application class with @HiltAndroidApp.
class FirebaseAuthApp : Application()