### Path Example with Java Backend

Path Application with sample Java Backend
* Gradle, Spark, Hibernate, H2

## Live Example
Live example on Heroku Free (please wait for wakeup): https://path-example-java.herokuapp.com/<br/>
Users: "admin" and "user" (without quotes), Password: "." (dot without quotes)

## Required Tools
* Git (https://git-scm.com/)
* Node and NPM (https://nodejs.org/)
* Java SDK 8 (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Installation
* Clone repository with Git
* Open a console/terminal window and change directory to frontend:
```
cd frontend
npm install
npm run ng build
```
Now all javascript files required for the frontend are built.

## Deployment
* Open a console/terminal window and change directory to backend:
```
cd backend
gradle run
```
* You may check the backend is running by opening http://localhost:4567/services/ping in a web browser
* You may check the frontend is running by opening http://localhost:4567/ in a web browser

## Options
Some options may be set by using a environment variable or Java system property before executing the commands above:
* PORT=xyz serves on a different port