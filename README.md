# Salon App

The code for this application currently sits in two categories. The classes which will be necessary for a full
application implementation, in its current state: Appointment, Client, Person, Service, Storable, and Stylist. The
second category represents the class and background work necessary to make a presentable UI. This includes the 
MainApp class and the fxml and css files. The css and fxml files, in addition to the jpg used for a background
are contained within the resources directory in the main folder. The java classes are contained in the Java directory
within the SRC file.

The code is structured in a manner that the java classes of our objects are used in the creation of tables for the database.
These include 'Appointment', 'Client', 'Person', 'Service', and 'Stylist'. The fxml scenes have controllers to provide 
functionality and direction. A Utility class titled 'DBUtils' was created to better organize the concepts of client registration, 
login, and scene change.

The non-functional requirements the application meets and the classes that satisfy them:
1) Incorporate measures for user privacy and data security - This was best accomplished through the utilization of 
the password fields for the LoginController and the RegisterController classes.
2) Straightforward design principles for a simple user interface - The Controller classes are another great example of 
this, as well as our fxml files that are designed for simple direction.
3) Prioritize reliability and the handling of errors and exceptions - Within the DBUtils class, there are many examples of error 
handling. For example, if a field is not entered or if a user enters the incorrect login information.
4) Scalable to allow for an increase in user numbers and service expansion - The use of the database and the according tables 
best show this requirement. Organization of increasing user numbers is made easy with the clientID parameter.

The functional requirements the application meets and the classes that satisfy them:
1) Allow returning users to login to their accounts - This is met through the LoginController and DBUtils classes.
2) Allow new users to create/register a new account - Met through the RegisterController, DBUtils, and creation of a CLient 
within a table for the database.
3) display the appointment/service/staff options - Met with the Appointment table and the AppointmentController class.
4) Allow the client to view or modify their appointments - Satisfied with the Home class and the AppointmentController class.
5) Allow for appointment cancellation - Satisfied with the Home class and information stored in the database.
6) Allow for users to logout of their account - Satisfied with the Home class.

This project is most easily accessible with the Intellij IDEA IDE and has been tested with the 
[community edition](https://www.jetbrains.com/idea/download).


In order to run this application, you will need to download and unzip the associated .zip file. Once the file has
been unzipped, in the top right of the IntelliJ Idea welcome screen click "Open." This should open a window wherein
you can navigate to the unzipped file and click the "Open" button in the lower right corner.

This project uses [Gradle](https://gradle.org) through the "Gradlew Wrapper" (this means that you don't need to
install) Gradle separately as its build tool and requires a Java
Development Kit version 17 If using IntelliJ IDEA, new versions of the JDK can be installed without leaving the IDE.
Open the settings pane by selecting "IntelliJ IDEA -> Preferences" on MacOS and "File -> Settings" on Windows. Then
on the left side of the settings pane expand "Build, Execution, Deployment" -> "Build Tools" -> "Gradle". Then in
the pane on the right near the bottom there is a dropdown menu labeled "Gradle JVM". Click on the dropdown, then on
"+ Add SDK" -> "Download JDK". In the new popup select 17 for version and then for version select
"Eclipse Temurin (AdoptOpenJDK HotSpot) 17.0.10". If you have a Mac with Apple Silicon select
"Eclipse Temurin (AdoptOpenJDK HotSpot) 17.0.10 aarch64".

After installing the JDK click on the elephant icon on the top right side IntelliJ to synchronize your project and
dependencies. Once that is complete, ensure that "Salon_App" run configuration is selected in the dropdown to the
left of the green play button on the top right side of the toolbar and then click the play button. The project will
compile and then start the application. To exit, simply "x" out of the user interface window.
