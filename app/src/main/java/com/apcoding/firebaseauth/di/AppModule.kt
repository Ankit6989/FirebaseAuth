package com.apcoding.firebaseauth.di

import com.apcoding.firebaseauth.data.AuthRepository
import com.apcoding.firebaseauth.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module

class AppModule {

    //This function is annotated with @Provides, which indicates that this function provides a dependency.
    @Provides
    //This function returns an instance of FirebaseAuth, which is provided by the Firebase Authentication library.
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    //This function returns an instance of AuthRepository, which is provided by the AuthRepositoryImpl class.
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}