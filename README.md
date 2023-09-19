# Paspla

It is an application that is used to get and process data from API and web pages.
A workflow can have multiple actions, that takes data as input, then process the data, and finally returns the processed data as output.
This a REST API, that you can consult using the Swagger interface.

## Available actions

- make an HTTP request
- send a mail

## How to run

### Development environment

Along the application there are other services that are required like a postgres database, a mail server, and for development sonarqube (with another database).
You can easily run containers by using the docker compose file that you can find in /deploy/dev.
Here are the following localhost ports and path on which you can access the different services :

- :8080/api/swagger-ui/index.html : the swagger interface to make your query on the API
- :8181 : the mail server interface to view the received mails
- :9000 : the Sonarqube interface to check the quality of your code

Then you can add a run configuration for a gradle to your favorite IDE :
`bootRun -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 --stacktrace`

### Required environment variables

These are the environment variables required by the app :

- SONAR_TOKEN : this is the token to connect to your sonar profile, it is used in the gradle build file

## How to contribute

It is not yet planned to board new contributors to the project, but you can ask the owner about it. Meanwhile you can create issues to express new feature you would like to see, bugs you
you encountered, opinion about the implementation or the design.

## TODO

In order of priority :

- have a decent code coverage
- fix issues found by Sonar
- an action that can parse an HTML page an return the data as JSON
- execute workflows at a certain time (like crontab)
- handle if a workflow is executed successfully
- handle if a workflow has failed
- handle the workflows that are missed (for example during a maintenance)
- make the user authenticate to use the service
- the users have a quota to use the API
- the users and admins have different rights to access different endpoints
- the users can create their own action by implementing a script
