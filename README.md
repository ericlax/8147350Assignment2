# NIT3213 Final Assignment — Android Application

**Student ID:** 8147350  
**Project Name:** 8147350Assignment2  
**Class Location:** Footscray  
**Platform:** Android  
**Programming Language:** Kotlin

## Project Overview

This project is an Android application developed for the NIT3213 final assignment. It integrates with the NIT3213 API to authenticate users, retrieve a collection of entities, and display detailed information.

The application uses Kotlin, XML layouts, Retrofit, Hilt dependency injection, and RecyclerView.

## Features

### Login Screen
- Accepts student ID and first name.
- Authenticates using the Footscray API endpoint.
- Displays error messages for invalid credentials and network failures.
- Navigates to the Dashboard after successful authentication.

### Dashboard Screen
- Retrieves entities using the keypass returned from authentication.
- Displays entities in a RecyclerView.
- Shows a summary of each entity without its description.
- Allows users to select an entity to view its details.

### Details Screen
- Displays all available properties of the selected entity.
- Includes the full description.
- Uses a scrollable layout for readability.

## Technologies Used

- Kotlin
- Android Studio
- XML Layouts
- Retrofit and Gson
- Hilt Dependency Injection
- Kotlin Coroutines
- RecyclerView
- ViewModel and StateFlow
- JUnit and MockK
- Gradle

## API Integration

**Base URL:** `https://nit3213apinew.onrender.com`

**Authentication:**
- Method: POST
- Endpoint: `/footscray/auth`
- Request: Student ID and first name
- Response: `keypass`

**Dashboard:**
- Method: GET
- Endpoint: `/dashboard/{keypass}`
- Response: List of entities and total count

## Project Structure

- `data/model` — Request and response data models
- `data/remote` — Retrofit API interface
- `data/repository` — Authentication repository
- `di` — Hilt dependency injection configuration
- `ui/login` — Login ViewModel
- `MainActivity.kt` — Login screen
- `DashboardActivity.kt` — Dashboard screen
- `DetailsActivity.kt` — Details screen
- `EntityAdapter.kt` — RecyclerView adapter

## Build and Run Instructions

1. Clone or download the repository.
2. Open the project in Android Studio.
3. Allow Gradle to synchronize and download dependencies.
4. Select an Android emulator or connected device running Android 7.0 (API 24) or newer.
5. Run the `app` configuration.
6. Enter your assigned student ID and case-sensitive first name.
7. Tap Login to access the Dashboard.
8. Select an entity to open its Details screen.

An internet connection is required to access the API.

## Unit Testing

The project includes three LoginViewModel unit tests:

1. `loginSuccess` — Verifies successful authentication and keypass handling.
2. `loginInvalidCredentials` — Verifies error handling for invalid credentials.
3. `loginNetworkError` — Verifies handling of network failures.

All three tests were run successfully during development.

To run the tests, right-click `LoginViewModelTest.kt` in Android Studio and select **Run**, or run the Gradle `testDebugUnitTest` task.

## Version Control

Git is used for version control, and the project is hosted on GitHub.

## Conclusion

This application demonstrates Android UI development, REST API integration, dependency injection, asynchronous programming, error handling, and unit testing.