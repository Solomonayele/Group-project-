# Salon App

This application's codebase has grown significantly from the last iteration. The classes aren't necessarily organized
in a meaningful way at the moment. The focus in this iteration was to meet the functionality requirements, as messy as the
project may end up. The Java classes are a mixture of container classes with their associated methods and fields, and 
controller classes responsible for the seemless transition between FXML scenes. In addition to the Java classes, many of the necessary
FXML documents and other image resources are stored seperately, each accessed by their associated controller class.
The css and fxml files, in addition to the jpg used for a background are contained within the resources directory in 
the main folder. The java classes are contained in the Java directory within the SRC file.

The code is structured in a manner that the java classes of our objects are used in the creation of tables for the database.
These include 'Appointment', 'Client', 'Service', and 'Stylist'. The fxml scenes have controllers to provide 
functionality and direction. A Utility class titled 'DBUtils' was created to better organize the concepts of client registration, 
login, and scene change. A file called 'Database' houses the 'Database' class and 'DBHelper.' Both of these classes are the backbone
to the login and appointment systems. 

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
compile and then start the application. 

HOW TO:

Once the program has been started and the front page has been launched, the user should see a 'Sign-In' button. The user
can advance to the login screen by pressing the 'Sign-In' button. Existing users may log into the system using previously supplied
credentials. Since the application is not currently hosted, any prior registered users may not appear. With that said, any users
registered on a particular machine will remain on that machine's database unless the database is altered by some external force.

New users may click the 'NEW CLIENT?' button to be directed to a registration page. The user will need to fill in the boxes and select
a date of birth from a date picker. A user may be accustomed to advancing to the next text field using the Return/Enter key, however it 
is the suggestion of the team to simply click the text box needed. Once each of these fields has been filled out, the user may click the
'Submit' button and be directed to the home page. If a user has accidentally clicked on the 'NEW CLIENT?' button, they may simple click 
'Return To Login' to return to the login page.

Alternatively, for testing purposes users may login with the email 'john.doe@example.com' and the password 'password123.' This will provide
access to the application for any user who is seeking to test features. 

Upon susscessful sign-in, the user will be directed to the About page of the Salon app. Information regarding contact info, etc. is listed on 
this page. This page also has the 'Log out' button, if a user wishes to log out of the Salon app. Near the top of the page are three tabs, including 
the current 'About' tab. If a user wishes to register an appointment they may click the 'SERVICES' tab. On this page, the user will be able to view 
available hairstyles as well as the associatedprice and description. To register for an appointment the user may click the 'Book Appointment' button 
at the bottom. 

Once a user has clicked the 'Book Appointment' button, they will be directed to a page to book their appointment. The Select Service, Select Stylist,
and Select Time are drop down menus that may be clicked and the appropriate selection made. The Select Date is a date picker whereby the user can select
a date in the future on which the appointment will be. Once the user is finished with their selection they may select 'next' which displays the info
from their selections and ask them to confirm. The user may click 'ok' or 'cancel.' If the user clicks 'ok' they will be shown a new box confirming that
their appointment has been booked for that particular service, stylist, date, and time. The user will then be redirected to the about page. 

If a user wishes to view their appointments, they may go to the 'View Appointment' tab at the top of the screen. This will display any previously 
made appointments and the associated data. If the user wishes to see the newest appointment they made on their view appointments screen, they
simply need to click the 'Refresh' button and the newest data regarding that user's appointments will be populated.

If a user wishes to cancel an appointment, they must select the appointment on the View Appointments tab. Once the appointment they wish to be 
cancelled is selected they may click the 'Cancel Appointment' button to remove that appointment from their appointment list. If a user fails to
select an appointment and pushes the 'Cancel Appointment' button, the program will not remove any appointments and an error will be throw. 

Again, to logout, the user must return to the 'About' tab tab and click the 'Log out' button. The user may alternatively hit the red x to close the
application.


