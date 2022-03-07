# school
School registration systems

Design and implement simple school registration system
- Assuming you already have a list of students
- Assuming you already have a list of courses
- A student can register to multiple courses
- A course can have multiple students enrolled in it.
- A course has 50 students maximum
- A student can register to 5 course maximum

Provide the following REST API:
- Create students CRUD
- Create courses CRUD
- Create API for students to register to courses
- Create abilities for user to view all relationships between students and courses
+ Filter all students with a specific course
+ Filter all courses for a specific student
+ Filter all courses without any students
+ Filter all students without any courses
-----------------------------------------------------------------------

Technology stack:
• Java
• Maven
• Spring Boot
• JUnit
• MySQL
• Liquibase
• swagger documentation
• jwt authorization

-----------------------------------------------------------------------
# swagger link
http://localhost:8080/swagger-ui/
Swagger authorization
**First execute signin request, hence copied the jwt token and paste in the text box of swagger authorization box
after that it will send authorization bearer token header for every request**


-----------------------------------------------------------------------
# Liquibase file generation while entity changes
Command is given below
**mvn liquibase:diff**


