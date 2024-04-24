# Rahber Prototype

This is an Android prototype for Rahber app.
![alt text](https://github.com/riyase/Rahber/blob/f4f85280d2cea4ed7d50fddbb3a7ed4a2071d53d/app/src/main/res/drawable/rahber.png?raw=true)


Development setup
- Android Studio Iguana | 2023.2.1
- Android SDK API Level 34
- VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.

Running instructions
-Install latest Android Studio
-Make sure the setup has API level installed in SDK Manager
-Build the project and run

The app has 3 Dashboard screens. 

COURSE Screen - lists all the courses with the following details
1. Course image
2. Course title
3. Course instructor name
4. Course short description

MY COURSES Screen - lists all the courses the current user enrolled to.
This lists the same details as the Course screen 

RECOMMENDED (NOT IMPLEMENTED)- lists all the courses recommended to the user based on the interests 
and courses user selected.

COURSE DETAILS Screen - displays a detailed information about a course which consists of the following
1. Course image
2. Course title
3. Course long description
4. List of video modules with module name, and thumbnail image.

The app is developed using MVVM Architecture which seperates the UI and Business logic using 3 layers
Model - Business logic / API integration
View - User interface
ViewModel - Connects the View and model and resposible for delivering updated data 
even after config changes

Technologies/ Libraries used
# Jetpack Compose (UI Developement)
# Hilt (Dependency Injection)
# Coil (Image loading library)
