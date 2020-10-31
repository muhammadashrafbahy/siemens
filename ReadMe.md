## siemens-plant-information  

This microservice implements a basic plant information using Java Spring technology and Postgres as a datasource. 

## prerequisite for this project 

  - Java runtime & JDK 
  - Docker & Docker-compose environment  

## Getting started

After cloning the repository :

```bash
    git clone git@github.com:esna-security/siemens-plant-information.git
    cd siemens-plant-information
```

to quickly get started and initialize the development environment, use the project runner command :

```bash
    chmod ug+x runner.sh
    ./runner.sh init 
```

which will install its dependencies (including a Postgres docker container)

Then use runner command:
```bash
   ./runner.sh dev
```

which will run the development environment and install its dependencies (including a postgres docker container), 
and immediately give you access to [the api](http://localhost:10000/swagger-ui.html) in development mode .

Or you can start dev runner with loading the test data:
```bash
   ./runner.sh dev loadData
```
Also you can run tests with runner command:
```bash
   ./runner.sh test
```
### Application configuration 

The application is currently configured for the following environments : development, functiona-test. 
For dev/test environments configuration are specified in resources.

After altering the configuration, make sure you create 3 the following postgres databases :

* plantInformation-dev
* plantInformation-functional-test

Not creating these databases will fail the next steps, since the application runs all tests directly through the database.

IMPORTANT: all properties can be overloaded via the system variables. For example `server.port` can be overloaded by the system variable `SERVER_PORT`


### Properties
Application properties
```properties
server.port=10000
prop.path=... #path to properties on the file system
```

Jwt properties
```properties
security.jwt.uri=/v1/auth/**
security.jwt.header=Authorizationsecurity.jwt.prefix=Bearer 
security.jwt.expiration=...
security.jwt.secret=...
security.jwt.admin.password=...
```

Postgres properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/plantinfo-dev?stringtype=unspecified
spring.datasource.username=...
spring.datasource.password=...
```

CORS properties
```properties
cors.enabled=true
cors.host=...
```

## Workflow
To use the application API you must be authorized.

Application provides base credentials authentication via password that are defined in properties. 
Property `security.jwt.admin.password` is responsible for the authentication. The default password(for dev environment) is `pass`
 
### Authentication workflow
Do `POST` call API `{host:port}/v1/auth/` with body:
```json
{
  "password": "..."
}
```
if password is matched then jwt token will be returned with expiration(in seconds) in response:
```json
{
  "token": "....",
  "expiration": 1000
}
```
Then the header with composite value `{prefix}token` should be added to each API call. Header is defined in property `security.jwt.header`. 
Prefix that is also defined in property `security.jwt.prefix`.
For example there are properties:
```properties
security.jwt.header=Authorization
security.jwt.prefix=Bearer 
``` 
API call should be done with header `Authorization` and with value `Bearer token`


##  Local development

### Running the memphis.service with development profile   

running the application with development profile :
```bash
    ./gradlew :core:bootRun -Dspring.profiles.active=dev --no-daemon
```
running the application with development profile and load test data :
```bash
    ./gradlew :core:bootRun -Dspring.profiles.active=dev -DloadData=true --no-daemon
```

### Running the memphis.service tests  

The memphis.service is covered with unit, integration and full BDD tests

running unit tests :

```bash
   ./gradlew :core:test --no-daemon
```

running integration tests:

```bash
   ./gradlew :integration-test:test --no-daemon
```

running the tests (all tests: unit tests and integration tests):

```bash
   ./gradlew test --no-daemon
```

## Api Documentation  

A swagger api documentation is avilable through visiting the documentation url at :

    {host:port}/swagger-ui.html 
## HAL Browser  

A HAL browser is available through visiting the documentation url at :
```
{host:port}/browser/index.html#/
```
You must be authorized to use HAL browser API. Check `Authentication workflow`

