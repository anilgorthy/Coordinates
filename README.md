Determining Distance
====================

### Environment/Libraries
1. Android Studio 3.5.1
	* API version info: `Compile SDK Version: 29`; `Min SDK Version: 23`; `Target SDK Version: 29`
1. [Retrofit](http://square.github.io/retrofit/) is the networking library of choice

### App Notes
1. The OMDb Search app fetches movie title results from [OMDb API](http://www.omdbapi.com/)
2. The app is built using the **Model-View-Presenter (MVP)** architecture where the core idea is a testable architecture by separating the application into various parts thus making the application easier to maintain and test individually
1. The app takes the approach of **package by layer** 
1. In the MVP architecture, the **View** (Activity) communicates with the **Presenter** for handling user interactions and the **Presenter** communicates with the **Model** to fetch the data from the APIs and present it back to the **View**
1. Tested the app on Pixel 2 (running OS 9) apart from an Emulator running API 26


### API Endpoint
1. OMDb endpoint appears to only return **10** results in a single API call and in order to get the next set of 10 results, you will need to add "page=2" as a query parameter, however, for the sake of this demo, I didn't enable pagination in the app 
1. The `Poster` URL may sometimes return **"N/A"** as opposed to a URL string so, I'm loading a placeholder image when the URL is not present
	* I did not look into other scenarios where the URL is not returned as expected
1. The demo app doesn't process the error status response returned by the API to display to the user

### Unit Tests
1. Ensured unit tests are written for **Presenter** which can be executed on the JVM
1. At this time, the demo doesn't handle Instrumentation tests