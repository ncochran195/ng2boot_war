This is the base project for angular2 with spring boot.  Included is a simple example with a service, a router, and some components.

Setup project: mvn eclipse:eclipse
Build: mvn clean install
Run: Copy .war from ./target/ to tomcat/webapps/ and start tomcat
                       OR
execute the .war from ./target/ with java -jar

Autobuild ./src/main/frontend to ./src/main/resources/static: ng build --prod --watch

Eclipse:
Should have some method of synchronizing transpiled bundles from ./src/main/resources/static to tomcat/webapps/app/WEB-INF/classes/static
(some synchronizing utility like filesync or similar)
(Mac users should use the "Sync Folders" application)

Intellij:
Use the internal tomcat server, files sync automatically