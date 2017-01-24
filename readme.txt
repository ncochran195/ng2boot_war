//--  INTRODUCTION  --//
This is the base project for angular2 with spring boot.  Included is a simple example with a service, a router, and some components.  Jquery and bootstrap are also included in the frontend.  Thymeleaf is used as the backend's view resolver.

//--  SETUP  --//
1) In pom.xml, change groupId, artifactId, name and description
2) Run mvn eclipse:eclipse
3) Run mvn clean install
4) Change or remove the server.contextPath option in ./src/main/resources/application.properties

//--  EXECUTION  --//
1) Copy .war from ./target/ to tomcat/webapps/ and start tomcat to use external tomcat
    OR
2) execute the .war from ./target/ with java -jar to use embedded tomcat
    OR
3) use internal tomcat server from eclipse/intellij

//--  DEVELOPMENT  --//
mvn clean install takes some time to run on this project due to the use of angular-cli for transpiling, so it is advised to setup your development environment to automatically transpile frontend changes and synchronize the output to tomcat.
1) Start the angular cli builder in watch mode and leave the command prompt running:
cd ./src/main/frontend
ng build --prod --watch
2) Next, setup file synchronization between ./src/main/resources/static and tomcat/webapps/app/WEB-INF/classes/static
2.A) Eclipse
    2.A.a) Windows - Use the filesync plugin and configure eclipse to automatically refresh the project
        -http://marketplace.eclipse.org/content/filesync
        -http://stackoverflow.com/questions/1212633/can-eclipse-refresh-resources-automatically
    2.A.b) Mac - Use the "Sync Folders" application
        -https://itunes.apple.com/us/app/sync-folders/id530573877?mt=12
2.B)Intellij - Use the embedded tomcat server, files sync automatically after the automated angular-cli build
3) You will still need to run mvn clean install for java code changes (and copy the war to tomcat, if using external tomcat).

If setup correctly, changes should show up in the browser within 5 seconds of saving the file.

//--  BACKEND EXAMPLE EXPLANATION  --//
The Spring Boot portion has a single controller to serve /greetings.json, which is consumed by the frontend. Additionally, there is the ExampleApplication class which is the servlet initializer for the application.

//--  FRONTEND EXAMPLE EXPLANATION  --//
The Angular 2 portion has examples of an injectible service, as well as handling callbacks with the observe/subscribe  pattern (DataService.ts/news.component.ts).  Additionally, there is a router (router.component.ts) that is used to route between two dummy components (table.component.ts and news.component.ts).  Also shown is how to use component-custom styles (app/styles/*) and global styles (app/styles.css).  Finally, responsive bootstrap is shown working (table.component.ts/html).  Each component has a boarder, to show the scope and composition of each component.
