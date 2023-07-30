# School Registration API by Micronaut
* Rest API to register students in courses and get the list of students registered in a course.
* The docker image is public in docker hub: https://hub.docker.com/r/helpxml/school-registration-micronaut
* To run it easly, just pull the image and run it with docker-compose.

### Ports:
* Server 8282 - Running by docker-compose 8989 
* Mysql 3306

### Swagger documentation
* [API DOC](http://localhost:8282/school-registration/v1/api-docs)
* http://localhost:8989/swagger/school-registration-by-micronaut-1.0.yml
* [SWAGGER UI](http://localhost:8282/swagger-ui.html)
* http://localhost:8989/swagger-ui.html

### Authentication
* Not implemented yet

### Students
* There are 3 students already created, STUDENT-1, STUDENT-2, STUDENT-3.
* The usernames are STUDENT1, STUDENT2, STUDENT3
* The passwords are 111, 222, 333

### Courses
There are 7 students already created, COURSE-1...COURSE-7

### How to run it
* Run the command: docker-compose up -d
* To stop: docker-compose down

### MAC WITH ARM ARCHITECTURE
Might be necessary to add new parameter in mysqlserver on docker-compose.yml
* platform: linux/x86_64
