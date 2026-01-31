How to Run the Project Locally

1. Download the Supported Desk ZIP file and extract it to your local system. 
   Open the extracted project root folder.

2. Check Ports
   Make sure the following ports are free:
	Backend: 8080
	Frontend: 3000

3. Create Database
	CREATE DATABASE minisupportdesk;

4. Backend Setup

5. Open the project in a Java-supported IDE (e.g., IntelliJ IDEA).
	project name is: support-desk

6. Let IntelliJ import Maven project.

7. Configure database credentials in application.properties:
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD

8. Set JWT secretKey in application.properties.
   spring.jwt.secretKey=YOUR_SECRET_KEY


9. (Optional but recommended) Run:
   mvn clean install

10. Run the Spring Boot application.
	Backend will run on [http://localhost:8080](http://localhost:8080).

11. Frontend Setup

12. Open the frontend folder in any editor (VSCode) or terminal:
   cd frontend-support-desk
   npm install
   npm start
Frontend will run on [http://localhost:3000](http://localhost:3000).

13. For Admin SignUp
	- Open PostMan or Api Test tool
	- POST - http://localhost:8080/api/signup/craete-admin
		body:{
			"fullName":"FULL_NAME",
			"username":"ENTER YOUR EMAIL",
			"password":"PASSWORD"
	}
