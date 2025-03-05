# Bookpedia
Bookpedia is a Compose Multiplatform application that runs on Android, iOS, and Desktop. It allows users to search for books, view their details, and manage their favorite book collection.
- When a book is searched, a request is made to the API using **Ktor**. The response is then mapped to the application's model using **Kotlin Serialization**.
- Book covers are displayed efficiently using the **Coil** image loading library.
- Dependencies are injected using **Koin**, following a modular and scalable architecture.  
- Favorite books are stored locally in a **Room Database**, allowing offline access.
- If a book is already marked as a favorite, its details are retrieved from the local database instead of making an additional API request, reducing network usage.

## Android Application
<img src="https://github.com/barissemerci/Bookpedia/blob/screenshots/screenshots/android_screenshots.png" width="600" height="400">

## iOS Application
<img src="https://github.com/barissemerci/Bookpedia/blob/screenshots/screenshots/ios_screenshots.png" width="550" height="400">

## Desktop Application
<p float="left">
<img src="https://github.com/barissemerci/Bookpedia/blob/screenshots/screenshots/desktop_screenshot_1.png" width="500" height="250">
<img src="https://github.com/barissemerci/Bookpedia/blob/screenshots/screenshots/desktop_screenshot_2.png" width="500" height="250">
<img src="https://github.com/barissemerci/Bookpedia/blob/screenshots/screenshots/desktop_screenshot_3.png" width="500" height="250">
</p>



## Technologies used
- Compose Multiplatform
- Jetpack Compose
- Clean Architecture
- MVI
- Koin
- Ktor
- Room
- Coil
- Coroutines
- Flow
- Navigation Component
