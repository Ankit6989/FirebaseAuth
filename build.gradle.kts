buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
 plugins {
        id("com.android.application") version "8.1.0" apply false
        id("com.android.library") version "7.2.1" apply false
        id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}

// This line declares a Gradle task named "clean." The type: Delete part specifies that the task is of type "Delete," which means it will perform file deletion operations. 
// This line registers a custom Gradle task named "clean." The task's name is specified as "clean," and it uses the Delete::class class reference to define the task's type.

tasks.register("clean", Delete::class) {

// This line is the action associated with the "clean" task. 
// It instructs Gradle to delete the "build" directory of the root project. 
// The "rootProject" is a reference to the main project in the Gradle build, and "buildDir" is a property that represents the build directory for the project.

    delete(rootProject.buildDir)

// This code is telling Gradle to create a task called "clean." When you run this task, it will delete a directory called the "build directory."
// This "build directory" is where all the compiled code and other things generated during the building of your project are stored. 
// So, running the "clean" task will remove these generated files, giving you a fresh start for your project's next build.
// It's like cleaning up the mess from previous builds before starting a new one.
}
