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

1)Both users are sent a notification from the backend and when the details are viewed, he is be able to see the other person’s name, email, and phone number.

2)On clicking on the notification the user is taken to a page where it shows that the request was successful

3)Both the users aren't not be matched with anyone else unless one user deletes their request. 

4)Both the users requests automatically gets deleted once they are matched 


Tech stack:
The app is made on Kotlin and Jetpack libraries. 
All network requests are made using Retrofit.
The UI for the Android app must be built using ConstraintLayouts. 
Own backend API service using django. All the major tasks such as matching of users and sending notifications take place on the backend.
All data in the backend service is stored in a NoSQL database, MongoDb. 
Uses Firebase Cloud Messaging to notify the user when a match is found.

# App functioning=>
If the user is not from bits

https://drive.google.com/file/d/14YJWO21ZvEenWQ86fU8V3X0rX5jS2C5i/view?usp=sharing

If the user is from bits

https://drive.google.com/file/d/14UuDxHfhJsLiKclwcamhkbJHr0nfmgYz/view?usp=sharing

On successful request 

https://drive.google.com/file/d/14NLNo-AUIO9Zy1EAdYR9RPrOFSd8EOct/view?usp=sharing

