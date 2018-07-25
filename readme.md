Introduction

Features
This sample contains two screens: a list of products and a detail view, that shows product reviews.

This example apo consists of two screens displaying data about fruit.
The first screen will display a list of fruit. When the user selects an item of fruit the application
should show the second screen containing more information about the fruit. Specifically, the price
(in pounds and pence) and the weight (in kilograms).
The user should be able to invoke a reloading of the data from the server.
The application will should fetch the list of fruit available from
https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.
json
Note: The returned units are pence, for the price, and grams, for the weight.
There is an additional requirement to record statistics as users interact with the app. This is done
by issuing GET requests to:
https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats
Event specific parameters should be appended to the URL as follows:
event=load ?� any network request
data=xxx - the time in ms for the complete request
event=display ?� when ever a screen is shown
data=xxx - the time (in ms) from when the user initiated a request that would show the screen to
the point where the screen has been shown
event=error ?� when ever there is a raised exception or application crash
data=xxx - something useful for a developer to use in response to �live issues�
For Example:
https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats
?event=load&data=100
https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats
?event=display&data=3000
https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/stats
?event=error&data=null%20pointer%20at%20line%205

The app uses a Model-View-ViewModel (MVVM) architecture for the presentation layer. 

Package Structure
api - that we have an API package where our
retrofit implementation to integrate
with the github API is conceived as a
simple class in here github service
which is like a an interface decorated
with the Metro sanitation - may come and

return a live data object a generic live
data with a generic arguments of API
response 

dao - database related classes including data access objects
di - dependency injection Dagger 2
dto - model
guiView - gui interface
repo - repository layer. repository to separate the logic that retrieves the data and maps it to the entity model from the business logic that acts on the model
ui - activities, adapters
utils - adapters used by retrofit, eg A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
viewmodel - view model for Main Activity
exceptions - exception handling for reporting crashes and exceptions
applicaiton - initials DaggerAppComponent and LoggingExceptionHandler

NetworkBoundResource
A generic class that can provide a resource backed by both the ROOM database and the network.
The NetworkBoundResource class is an abstract class with the following abstract method:
saveCallResult: This method is responsible for updating/inserting the result of the API into the local database. This method will be called when the data from the remote server is successfully fetched.
shouldFetch: Based on implementation, this method should return true if it is needed to fetch the data from a remote server. For example when the data is outdated. Else it should return false.
loadFromDb: This method is responsible for returning the data from the local database.
createCall: This method is responsible for creating a remote server call which is responsible for fetching the data from the server. Later we will see how to wrap this result in LiveData.

After implementing these abstract methods, we can call the getAsLiveData method of NetworkBoundResource class, which returns a LiveData object that can be observed for changes.




TODO
complete tests
remove Auditor from NetworkBoundResource