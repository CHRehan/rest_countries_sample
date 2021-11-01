## MAJORITY assignment solution in Kotlin via MVVM Repository Pattern.

<img align="right"  src="demo.gif" width="250"/>

REST countries sample app that loads information from [REST countries
API V3](https://restcountries.com/#api-endpoints-v3-all) to show an 
approach to using some of the best practices in Android Development.

Cache Strategy
--------------
On the very first launch app send the request to the API and fetch the data,after successfully fetch save all the data in the local cache  DataStoredPreference. On the next launch app will check need to fetch the data from the api or show the local storage data.
it's depends on the fetch interval defined in `FETCH_INTERVAL_IN_SEC` in `Constants.kt`.
Current interval is 24 Hours.You can change it from `Constants.kt`.

Libraries Used
--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [Android KTX][1] - Write more concise, idiomatic Kotlin code.
  * [Test][2] - An Android testing framework for unit and runtime UI tests.
* [Architecture][3] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][4] - Declaratively bind observable data to UI elements.
  * [Lifecycles][5] - Create a UI that automatically responds to lifecycle events.
  * [LiveData][6] - Build data objects that notify views when the underlying database changes.
  * [Navigation][7] - Handle everything needed for in-app navigation.
  * [Room][8] - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel][9] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
    asynchronous tasks for optimal execution.
* [UI][11] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][12] - Move widgets and transition between screens.
  * [Fragment][13] - A basic unit of composable UI.
  * [Layout][14] - Lay out widgets using different algorithms.
* Third party and miscellaneous libraries
  * [Picasso][15] for image loading
  * [Hilt][16]: for [dependency injection][17]
  * [Kotlin Coroutines][18] for managing background threads with simplified code and reducing needs for callbacks
  * [SSP][19] An android lib that provides a new size unit - ssp (scalable sp). This size unit scales with the screen size based on the sp size unit (for texts). It can help Android developers with supporting multiple screens.
  * [SDP][20] An android lib that provides a new size unit - sdp (scalable dp). This size unit scales with the screen size. It can help Android developers with supporting multiple screens.

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/kotlin/ktx
[2]: https://developer.android.com/training/testing/
[3]: https://developer.android.com/jetpack/arch/
[4]: https://developer.android.com/topic/libraries/data-binding/
[5]: https://developer.android.com/topic/libraries/architecture/lifecycle
[6]: https://developer.android.com/topic/libraries/architecture/livedata
[7]: https://developer.android.com/topic/libraries/architecture/navigation/
[8]: https://developer.android.com/topic/libraries/architecture/room
[9]: https://developer.android.com/topic/libraries/architecture/viewmodel
[11]: https://developer.android.com/guide/topics/ui
[12]: https://developer.android.com/training/animation/
[13]: https://developer.android.com/guide/components/fragments
[14]: https://developer.android.com/guide/topics/ui/declaring-layout
[15]: https://square.github.io/picasso/
[16]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[17]: https://developer.android.com/training/dependency-injection/hilt-android
[18]: https://developer.android.com/training/dependency-injection
[19]: https://github.com/intuit/ssp
[20]: https://github.com/intuit/sdp

Upcoming features
-----------------
Updates will include incorporating additional Jetpack components and updating existing components
as the component libraries evolve.

TestCases are pending due to very tight work schedule these days.

API Response
------------
Countries API return alot of data but currently I am using this data.
Sample JSON.

```json

[
  {
    "name": {
      "common": "Malaysia",
      "official": "Malaysia"
    },
    "unMember": true,
    "currencies": {
      "MYR": {
        "name": "Malaysian ringgit",
        "symbol": "RM"
      }
    },
    "capital": [
      "Kuala Lumpur"
    ],
    "region": "Asia",
    "subregion": "South-Eastern Asia",
    "languages": {
      "eng": "English",
      "msa": "Malay"
    },
    "population": 32365998,
    "timezones": [
      "UTC+08:00"
    ],
    "continents": [
      "Asia"
    ],
    "flags": {
      "png": "https://flagcdn.com/w320/my.png"
    },
    "startOfWeek": "sunday"
  }
]
```

MVVM Repository Pattern
------------------------
The repository pattern is a structural design pattern. 
It’s instrumental for organizing how you access data. 
It also helps divide concerns into smaller parts. 

<img align="center"  src="repository-pattern.png" width="400"/>  

Repository operations delegate to a relevant datasource. 
Datasources can be remote or local. The repository operation has logic 
that determines the relevance of a given datasource. 
For instance, the repository can provide a value from a local 
datasource or ask a remote datasource to load from the network.

Android Studio IDE setup
------------------------
For development, the latest version of Android Studio is required. The latest version can be
downloaded from [here](https://developer.android.com/studio/).

All the dependencies are declared in the `build.gradle` file. No need to take any extra measure. 
Just import this project via Android Studio :)


