
# Library Bookkeeping system 📚

Here's a __CRUD__ web application for librarians. They now have the ability to register readers, give them books, and release books (after the reader returns the book back to the library).

## Tech Stack

**Client:** Thymeleaf, HTML

**Server:** Java, Spring Boot, MVC, JDBC


## A few words about this project 👀
This project was challanging for me like any others. I stumbled across a huge number of errors. I especially had problems with JOIN SQL queries.

Also, this project uses N tier architecture. I wanted to try to create a project using this architecture and achieve a weak connectivity between all the entities within the project. 
It was very confusing for me to pass data from one class to another, because now there are additional layers between the endpoints just to achieve this very weak connectivity
## Installation 🔧

If you want to test out the project just simply clone the repository, and create some test database.

After that you should connect the project to your database by typing the URL of your server, username, and password.

So it eventually should look like this:

```
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/your_url
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.mvc.hiddenmethod.filter.enabled=true
```
    
And you're all set.
