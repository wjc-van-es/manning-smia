## Spring Microservices in Action - Second Edition. Chapter 4

## Introduction

Code used on the 4th Chapter of the Spring Microservices in Action - Second Edition Manning publication book. This code contains the Licensing Microservice.

## Initial Configuration

1.	Apache Maven (http://maven.apache.org)
2.	Git Client (http://git-scm.com)
3.  Docker(https://www.docker.com/products/docker-desktop)

## How To Use

To clone and run this application, you'll need [Git](https://git-scm.com), [Maven](https://maven.apache.org/), [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html). From your command line:

```bash
# Clone this repository
$ git clone https://github.com/ihuaylupo/manning-smia

# Go into the repository
$ cd chapter4/licensing-service

# To build the code examples for Chapter 4 as a docker image, open a command-line 
# window and execute the following command:
$ mvn clean package dockerfile:build

# Run the app using the docker-compose up command. Remember, you need to execute
#the command on the root folder of the project where the docker-compose.yml is.
$ docker-compose up
```

## Contact

I'd like you to send me an email on <illaryhs@gmail.com> about anything you'd want to say about this software.

### Contributing
Feel free to file an issue if it doesn't work for your code sample. Thanks.

## Notes

### To make it work

In the IntelliJ project I created I had to set the File > Project Structure > Project Setting > Project SDK 11 (instead of 8) So the maven settings, which is set to use the project SDK uses the right version.

```bash
~/resources/git/manning-smia/chapter4/licensing-service$ mvn clean package dockerfile:build
```

 now succeeded.

From the IntelliJ Maven view dockerfile:push did not succeed. Even after adding the licensing-service repository to our DockerHub account and changing the 

```xml
<docker.image.prefix>ostock</docker.image.prefix>
```

 property in the pom file to 

```xml
<docker.repo.account>wjcvanes</docker.repo.account>
```

 (renaming it to more clearly represent its meaning).

Running it as docker CLI command from a terminal did succeed:

```bash
~/resources/git/manning-smia/chapter4/licensing-service$ docker image push wjcvanes/licensing-service:0.0.1-SNAPSHOT
```

From the terminal

``~/resources/git/manning-smia/chapter4/licensing-service$ docker-compose up``

From a browser we could call the GET REST call ``http://localhost:8080/v1/organization/optimaGrowth/license/1998`` where you can use any value for ``{organizationId}`` and ``{licenseId}`` these are just echoed back in the JSON response body at this stage of development.

From another terminal we could check several docker specific things

```bash
~/resources/git/manning-smia/chapter4/licensing-service$ docker image ls
~/resources/git/manning-smia/chapter4/licensing-service$ docker-compose ps
~/resources/git/manning-smia/chapter4/licensing-service$ docker network ls
~/resources/git/manning-smia/chapter4/licensing-service$ docker network inspect licensing-service_default
~/resources/git/manning-smia/chapter4/licensing-service$ docker ps
```

Finally we can take down the container and remove it with
 ``~/resources/git/manning-smia/chapter4/licensing-service$ docker-compose down``
which shuts down and remove the container.

### Observations

Chapter 4 describes several ways to create an image from the maven project containing the microservice called licensing-service. From these the code example of ~/resources/git/manning-smia/chapter4/licensing-service appears to show the second example using the com.spotify:dockerfile-maven-plugin with the multi-stage Dockerfile.