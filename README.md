# Getting Started
This app exposes the apis for getting optimal team for provided cleaning job.

### Code Usage 
**Build it** : *mvnw.cmd clean install*

**Run It** : *java -jar target\workforce-optimization-service-0.0.1-SNAPSHOT.jar*

**API Documentation** http://localhost:8080/api/swagger-ui.html

**API Usage (via Postman)**
 
  * Request URL : http://localhost:8080/api/wo/team
  * Request Body:
   ```
   {
      "rooms" : [24, 28],
      "senior": 10,
      "junior": 6
   }
   ```
  * Response Body:
   ```
      [
          {
              "seniorCleaners": 2,
              "juniorCleaners": 1
          },
          {
              "seniorCleaners": 1,
              "juniorCleaners": 3
          }
      ]
   ```

**Publish Sonar Results** : *mvnw.cmd clean install sonar:sonar -Dsonar.projectKey={projectKey}  -Dsonar.organization={organization}  -Dsonar.host.url={host}  -Dsonar.login={login}

**Build Docker Container** : *mvnw.cmd clean install dockerfile:build*

### Dev Operations
[WOS Travis CI Build](https://travis-ci.org/anant-pawar/workforce-optimization-service)
: Have integrated code repo with Travis CI which builds the app does following :

* perform and publish, code analysis and code coverage result to sonar cloud.
    * [WOS Sonar Report](https://sonarcloud.io/dashboard?id=anant-pawar_workforce-optimization-service)
* build and publish docker container to docker hub
    * [WOS Docker Hub](https://cloud.docker.com/u/anantpawar/repository/docker/anantpawar/workforce-optimization-service) 
* deployment of docker container is done manually as of now.

# Live Instance
Have hosted the app on a EC2 instance(has attached elastic ip *35.158.246.194*).

**API Documentation** [WOS Live Service](http://35.158.246.194:8080/api/swagger-ui.html)

### Guides
Refereed following guide's for development:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Spring Boot with Docker](https://spring.io/guides/gs/spring-boot-docker/)

