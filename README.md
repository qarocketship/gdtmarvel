This repository contains a Java-Playwright test automation framework that uses Cucumber and TestNG, 
Maven is used as the build and dependency management tool. 

## Project Setup Instructions

**Prerequisites**  
Before you begin, ensure you have the following tools installed on your local machine:

1. Download and install JDK 11 or higher from Oracle JDK or use OpenJDK.
   Command to verify Java installation: `java -version`
2. Download and install Maven from Apache Maven.
   Command to verify Maven installation: `mvn -version`
3. Download and install Node.js from Node.js Official Website.
   Command to verify Node.js installation: `node -v`
4. Use IntelliJ IDEA or any other IDE of your choice with support for Java and Playwright.

**Project Setup**  
Step 1: Clone the project repository to your local machine  
Step 2: Once the repository is cloned, navigate to the project root directory, and use Maven to install the required dependencies:
`mvn clean install`  
This will download all the dependencies specified in the `pom.xml` file   
Step 3: Open the project in the IDE and run the Tests from TestRunner class  
Step 4: After the test run, the report is gererated in Report folder. 
Right-click on the html report and open it in a browser
