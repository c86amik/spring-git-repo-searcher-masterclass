# spring-git-repo-searcher-masterclass

## A Spring Boot application to search GitHub Repositories

### Software Required
* [Java 17](https://www.openlogic.com/openjdk-downloads?page=0)
* [IntelliJ](https://www.jetbrains.com/idea/download/?section=windows) or [Spring tool Suite](https://spring.io/tools) or [Eclipse](https://www.eclipse.org/downloads/packages/)
* [Apache Maven](https://maven.apache.org/download.cgi)
* [Git Bash](https://git-scm.com/downloads)
* [PostGreSQL](https://www.postgresql.org/download/)
* [DBeaver](https://dbeaver.io/download/) - DBeaver. A DB Client
* [Postman](https://www.postman.com/downloads/)

### Steps to execute the DB Scripts
Run the below scripts in any of the clients either in pgAdmin4 or in DBeaver

* create-github-database.sql - It will create the database named <strong>github</strong>
* I have used spring.jpa.hibernate.ddl-auto=update as it will automatically create the table named as <strong>githubrepositories</strong>
* But in prod environment this property spring.jpa.hibernate.ddl-auto=validate and will provide the DDL query for underlying table <strong>githubrepositories</strong>.

### Steps to clone and run the application
* Install Java 17.
* Install PostGreSQL. Complete installation steps of [PostGreSQL](https://www.postgresql.org/download/windows/) are provided
* Open Git Bash or even you can open Command Prompt (if you are using Windows) or Terminal (if you are using MAC) in your machine
* Clone the application from github.com as   
  <code>git clone https://github.com/c86amik/spring-git-repo-searcher-masterclass.git</code>
* Open <strong>InelliJ</strong> and import the application as <strong>Maven</strong> project
* After the application got successfully imported in <strong>IntelliJ</strong>
* Right Click on the application, select the <strong>Run As</strong> option, and then select <strong>Spring Boot App</strong>
* The application will start in the port <strong>7125</strong>
* Open the Postman and test the REST Endpoints
* But in case of <strong>IntelliJ</strong> you click on File -> Open option and import the project.
* Once imported click the Maven Perspective available at the top right corner of IntelliJ.
* Run the <code>mvn clean install</code> command by setting the JDK to Java 17 and Maven as installed locally in your machine.
* After that click on the New/Edit Configurations at the top right corner of IntelliJ.
* It will open a pop-up box select the main class of your application, provide the proper JDK as Java 17 and click on Apply button and then run it. It will run as the Spring Boot Application.

### Testing using Postman
<ol>
<li><strong>Search GitHub Repositories</strong> - http://localhost:7125/api/github/search</li>
<li><strong>Retrieve Stored Results</strong> - http://localhost:7125/api/github/repositories?language=Java&minStars=0&sort=stars</li>
</ol>

#### Dummy JSON object
* Body for the <strong>POST</strong> method for Searching GitHub Repositories  
  <code>{
  "query": "spring boot",
  "language": "Java",
  "sort": "stars"
  }</code>
* Response object for the <strong>POST</strong> method for Searching GitHub Repositories  
  <code>{
  "message": "Repositories fetched and saved successfully",
  "repositories": [
  {
  "id": 660003842,
  "name": "boardEx1",
  "description": "https://congsong.tistory.com/category/Spring%20Boot 보면서 따라가기",
  "owner": "ruhjy",
  "language": "Java",
  "stars": 0,
  "forks": 0,
  "lastUpdated": "2023-06-29T03:20:32Z"
  },
  {
  "id": 629389845,
  "name": "blog_spring",
  "description": "https://hel-p.tistory.com/category/%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/Spring%20boot",
  "owner": "KIMB0B",
  "language": "Java",
  "stars": 0,
  "forks": 0,
  "lastUpdated": "2023-04-18T08:08:17Z"
  },
  {
  "id": 197412175,
  "name": "thymeleafFormSample",
  "description": "http://ziqoo.com/wiki/index.php?Spring%20Boot%20Thymeleaf%20form%A5%B5%A5%F3%A5%D7%A5%EB (NOTE:I don't own this sample program)",
  "owner": "koniemon0618",
  "language": "Java",
  "stars": 0,
  "forks": 0,
  "lastUpdated": "2019-07-17T15:07:27Z"
  }
  ]
  }</code>
* Body for the <strong>GET</strong> method for Retrieve Stored Results  
  <code>{
  "repositories": [
  {
  "id": 660003842,
  "name": "boardEx1",
  "description": "https://congsong.tistory.com/category/Spring%20Boot 보면서 따라가기",
  "owner": "ruhjy",
  "language": "Java",
  "stars": 0,
  "forks": 0,
  "lastUpdated": "2023-06-29T03:20:32Z"
  },
  {
  "id": 629389845,
  "name": "blog_spring",
  "description": "https://hel-p.tistory.com/category/%ED%94%84%EB%A0%88%EC%9E%84%EC%9B%8C%ED%81%AC/Spring%20boot",
  "owner": "KIMB0B",
  "language": "Java",
  "stars": 0,
  "forks": 0,
  "lastUpdated": "2023-04-18T08:08:17Z"
  },
  {
  "id": 197412175,
  "name": "thymeleafFormSample",
  "description": "http://ziqoo.com/wiki/index.php?Spring%20Boot%20Thymeleaf%20form%A5%B5%A5%F3%A5%D7%A5%EB (NOTE:I don't own this sample program)",
  "owner": "koniemon0618",
  "language": "Java",
  "stars": 0,
  "forks": 0,
  "lastUpdated": "2019-07-17T15:07:27Z"
  }
  ]
  }</code>