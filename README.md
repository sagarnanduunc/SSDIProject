# SSDIProject

This project was developed using IntelliJ. As such, we suggest using IntelliJ to run it, although any method that uses Maven as a build tool should work. If you do run the project through IntelliJ, you will need to open it with ```SSDIWebProject``` as the root folder.

## Before Running

For the project to behave correctly, you'll need to set up a MySQL database named ```renting```, then run the provided ```DatabaseInit.sql``` file to create the appropriate tables and populate the domain tables with the correct values. You will also need to edit the username and password fields in the ```application.properties``` file, located in ```src/main/resources```, to match your MySQL credentials.

If you wish to run tests, you will also need to create an additional MySQL database named ```rentaltest``` and populate it with tables using the same script as before. If you are running tests for a DAO, you will need to alter the calls to ```dataSource.setUsername()``` and ```dataSource.setPassword()``` to use your MySQL credentials.

## Viewing the Website

Once the Spring server is running, simply navigate to ```localhost:8080``` in any web browser that supports HTML5; the server will automatically load up the homepage for you.
