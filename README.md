# swapfrontend

Functionality:

User signs in with Google. 
Allow access only to BITS email addresses. 
Then take their phone numbers and verify via Firebase OTP phone number verification. 
Authenticate the sign in with your backend server and connect the phone number to the same account.
The feature set includes:
1) Adding a request (a user can add multiple),
2) Viewing your requests, 
3) Viewing details for your request
4) Deleting requests. 
5) Other users must not be able to see other requests.
6)Matching should be on a first request, first match basis. 

When two users are matched:

1)Both users should be sent a notification from the backend and when the details are viewed, he should be able to see the other person’s name, email, and phone number.
2)On clicking on the notification the user is taken to a page where it shows that the request was successful
3)Both the users should not be matched with anyone else unless one user deletes their request. 
4)Both the users requests automatically gets deleted once they are matched 


Tech stack:
The app is made on Kotlin and Jetpack libraries. 
All network requests must use Retrofit.
The UI for the Android app must be built using ConstraintLayouts. 
Own backend API service using django. All the major tasks such as matching of users and sending notifications must take place on the backend.
All data in the backend service needs to be stored in a NoSQL database, MongoDb. 
Uses Firebase Cloud Messaging to notify the user when a match is found.
