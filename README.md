# Rahber Prototype

This is an Android prototype for Rahber app.
![Rahber](https://rahbertheadvisor.com/)

The app has 3 screens
Course screen - lists all the courses with the following details
1. Course image
2. Course title
3. Course instructor name
4. Course short description

My Courses Screen - lists all the courses the current user enrolled to.
This lists the same details as the Course screen 

Recommended (NOT IMPLEMENTED)- lists all the courses recommended to the user based on the interests 
and courses user selected.

The app is developed using MVVM Architecture which seperates the UI and Business logic using 3 layers
Model - Business logic / API integration
View - User interface
ViewModel - Connects the View and model and resposible for delivering updated data 
even after config changes

Technologies/ Libraries used
# Jetpack Compose (UI Developement)
# Hilt (Dependency Injection)
# ViewModel (Connects the View and the Model)
# Coil (Image loading library)
# Bottom Navigation in jetpack compose

![alt text](https://github.com/DaaniDev/jetpack_compose_animations/blob/master/images/bottom_navigation/exoplayer_output.png?raw=true)