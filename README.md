# LocalPicDump
Web-server, site and userscript for 2ch.hk to store pics on localhost

#Requires 
  - jre 8
  - Grails 2.5.4 (grails 3 probably not supported)
  - MySQL

#TODO
  - Front-end

#Compilation and launch
  1. Set db credentials in /grails-app/conf/DataSource.groovy (Open with any text editor) 
    * url = jdbc:mysql://localhost:3306/%yourDatabaseName%
    * username = "%Your MySQL username%"
    * password = "%Your MySQL password%"
    * To upload pics > 4MB you need to set **max_allowed_packet** MySQL variable
  2. Execute grails prod run-app command in the root directory of project
  3. Applictaion now avalible on **http://localhost:1337/LocalPicDumb**
  4. To install userscript just drag-n-drop **localpicdump.user.js** file on **chrome://extension** page
