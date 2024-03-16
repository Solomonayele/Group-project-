# Salon App

The code for this application currently sits in two categories. The classes which will be necessary for a full
application implementation, in its current state: Appointment, Client, Person, Service, Storable, and Stylist. The
second category represents the class and background work necessary to make a presentable UI. This includes the 
MainApp class and the fxml and css files. The css and fxml files, in addition to the jpg used for a background
are contained within the resouces directory in the main folder. The java classes are contained in the Java directory
within the main directory.

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
