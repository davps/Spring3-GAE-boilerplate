Complete Google App Engine + Spring 3 Web App Example
===============================================================================

This repository contains best practices for an JEE (Spring based) application architecture. It's a great boilerplate to start JEE web apps and deploy them in GAE. 

### Introduction

This sample web app integrate the following technologies, methodologies and tools:

* Google App Engine (Java) for its back end
* Objectify for persistence layer
* Java Classes organized in the following packages 
	+ Domain
	+ Repository (with the DAO pattern)
	+ Service
	+ DTO (to adapt the domain objects for the Spring MVC models)
	+ Controller (using Spring MVC)
* RESTful Spring MVC web controllers
* i18n
* JSTL
* Apache Tiles
* JQuery
* Twitter Bootstrap
* Spring Security (with customized user accounts)
* Test Driven development (Test units and integration tests)

You can learn a lot just reading the source code. 

Getting Started
---------------

### Running the app

To run the app at localhost using maven type the following command in your terminal, at the root folder of the project:
``` 
mvn gae:run
```
And launch your web browser to `http://localhost:8000/` and sign in with a default user:

``` 
User: admin
Password: admin
```

``` 
User: user
Password: user
```

Each user has a different role (for authentication and authorization purposes).


### Deploying the app

To deploy the app on GAE change fill the following tag src/main/webapp/WEB-INF/appengine-web and change with your application name:
``` 
<application>example</application>
```
and type the following maven command in your terminal, at the root folder:
``` 
mvn gae:deploy
```

### Using Eclipse

If you use Eclipse, type the following command-line mvn command to create your .classpath file: 
``` 
mvn eclipse:eclipse
```

That's all.

### Default file system structure
``` 
+---src
   +---main
   ¦   +---java
   ¦   ¦   +---com
   ¦   ¦       +---namespace
   ¦   ¦           +---domain
   ¦   ¦           +---repository
   ¦   ¦           +---service
   ¦   ¦           ¦   +---dto
   ¦   ¦           ¦   +---validator
   ¦   ¦           +---util
   ¦   ¦           +---web
   ¦   +---resources
   ¦   ¦   +---META-INF
   ¦   ¦       +---spring
   ¦   +---webapp
   ¦       +---images
   ¦       +---js
   ¦       +---META-INF
   ¦       +---styles
   ¦       +---WEB-INF
   ¦           +---i18n
   ¦           +---layouts
   ¦           +---spring
   ¦           ¦   +---exampleServlet
   ¦           +---views
   ¦               +---account
   ¦               ¦   +---settings
   ¦               +---commons
   ¦               +---users
   ¦                   +---create
   ¦                   +---disabled
   ¦                   +---enabled
   ¦                   +---update
   +---test
       +---java
       ¦   +---com
       ¦       +---namespace
       ¦           +---domain
       ¦           +---repository
       ¦           ¦   +---mock
       ¦           +---service
       ¦           ¦   +---dto
       ¦           ¦   +---mock
       ¦           ¦   +---validator
       ¦           +---util
       ¦           +---web
       +---resources
```


### TODO list

* Better integration of Apache Tiles with Spring Framework
* Improve the test units, uncomment test cases and fix them
* Fill all the internationalization fields 
* White the pagination for the user lists
* Creation of a custom login form
* Better error handling for the DAO layer (using GAE specific Exceptions)
