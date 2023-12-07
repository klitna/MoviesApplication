# MoviesApplication


MoviesApp is an Android app built using Jetpack Compose and Retrofit to interact with the TMDB API. This app allows you to explore show lists of movies and explore the details.

## Features

- View detailed film information.
- Filter lists of films.
- Utilize the power of Jetpack Compose for building a modern and responsive user interface.
- Fetch data from the TMDB API using Retrofit for seamless network communication.

## How to Build and Run

1. Clone this repository to your local machine.
2. Open the project using Android Studio.
3. Build and run the app on an emulator or physical device.

## Download APK

You can download the latest APK of the MoviesApp from the following link:

[DOWNLOAD APK](https://github.com/klitna/MoviesApplication/files/13603847/app.zip)




# App Dependencies

Below is a list of dependencies used in your Android app along with the reasons for their inclusion:

## Core Dependencies

- `androidx.core:core-ktx`, `androidx.appcompat:appcompat`, `com.google.android.material:material`, `androidx.constraintlayout:constraintlayout`: These libraries provide essential building blocks for Android UI development, ensuring consistent behavior and appearance across different Android versions and devices.

- `androidx.lifecycle:lifecycle-viewmodel-ktx`: This library provides ViewModel extensions for Kotlin, making it easier to manage UI-related data across configuration changes and lifecycle events.

## Testing Dependencies

- `junit:junit`, `androidx.test.ext:junit`, `androidx.test.espresso:espresso-core`: These libraries are essential for writing unit tests and UI tests to ensure the quality and correctness of your app.

## Coroutines

- `org.jetbrains.kotlinx:kotlinx-coroutines-core`, `org.jetbrains.kotlinx:kotlinx-coroutines-android`, `org.jetbrains.kotlinx:kotlinx-coroutines-jdk8`: Coroutines simplify asynchronous programming, making it more readable and manageable. They are crucial for performing network requests and other background tasks without blocking the UI thread.

## Retrofit and Moshi

- `com.squareup.retrofit2:retrofit`, `com.squareup.retrofit2:converter-gson`, `com.squareup.moshi:moshi-kotlin`: These libraries facilitate network communication by providing a simple and efficient way to define and consume APIs. Retrofit handles the network requests, while Moshi (or Gson) converts JSON responses to Kotlin objects.

## Dagger Hilt

- `com.google.dagger:hilt-android`, `androidx.hilt:hilt-navigation-compose`, `com.google.dagger:hilt-android-compiler`: Dagger Hilt simplifies dependency injection by generating code to manage and provide dependencies throughout your app. Hilt's integration with navigation and Compose makes dependency injection seamless.

## Jetpack Compose

- `androidx.compose:compose-bom`, `androidx.lifecycle:lifecycle-viewmodel-compose`, `androidx.compose.runtime:runtime-livedata`, `androidx.compose.ui:ui`, `androidx.compose.foundation:foundation`, `io.coil-kt:coil-compose`, `androidx.navigation:navigation-compose`, `androidx.compose.material:material`: These libraries constitute Jetpack Compose, a modern UI toolkit that simplifies UI development by using a declarative syntax. These libraries provide tools for creating responsive and dynamic UIs, handling navigation, and integrating Material Design.

## Material Design 3

- `androidx.compose.material3:material3`: Material Design 3 (MD3) extends Material Design principles with new components and features. This library allows you to implement the latest Material Design guidelines in your app's UI.

## UI Testing

- `androidx.compose.ui:ui-test-junit4`, `androidx.compose.ui:ui-test-manifest`: These libraries provide tools for writing UI tests for your Jetpack Compose components, ensuring that your UI behaves correctly across different scenarios.

The selected dependencies contribute to various aspects of your app's development, including UI creation, navigation, networking, testing, and dependency injection. Each dependency enhances the development process and ensures that your app is well-designed, functional, and maintainable.
