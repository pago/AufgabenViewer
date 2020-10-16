# AufgabenViewer

A Java/Swing application built in 2010 for creating dynamic math tasks for students (age 6 - 18) for ILLUSTRATION PURPOSES ONLY

I wrote this application in 2010 while working as a private tutor for children aged 6 to 18. The application was intended to generate tasks for the students, mostly focused on math.

This code is not intended to be reused. I have no idea if it still runs.
What is interesting about it is its architecture. It leverages Guice (Dependency Injection) in combinations with an Actor model (non-blocking async code execution) and very cleanly separates the UI from the business logic.

I forgot most about how this tech stack (Java/Swing) works. The executable files might are severely outdated and I have no idea if they are still working.
This application is not particularly useful and only renders (and prints) a bunch of PDF files. The `/aufgaben` directory was generated using PHP and Latex and the application creating it isn't part of this repository.
