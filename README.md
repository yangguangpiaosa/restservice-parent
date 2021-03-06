##restservice(-parent) a restful webservice project implemented by Jersey.
###Build Status:
Branches:

master:
[![Build Status](https://travis-ci.org/yangguangpiaosa/restservice-parent.svg?branch=master)](https://travis-ci.org/yangguangpiaosa/restservice-parent)

dev:
[![Build Status](https://travis-ci.org/yangguangpiaosa/restservice-parent.svg?branch=dev)](https://travis-ci.org/yangguangpiaosa/restservice-parent)

========
###Project Structure:
* restservice-parent is a aggregate project.
* restservice-service is main project. It consists of almost all the code. We define all the services in it.
* restservice-test is an external project for service test.

========
###Change Log:
* 2014-09-17  Initialize project.
* 2014-10-07  Add Spring support, add demo service(i.e. user service) code.
* 2014-10-13  Add unit test(easymock, spring test), add jaxb parser.
* 2014-10-14  Add demo service test, add json schema test.

========
###Run it:
We imagine that your environment is already there, so let's begin.
Move to the directory of restservice-service, then
Command Line:
```javascript
mvn clean tomcat7:run
```
After that, you can check the result(http://localhost:8088/service/api/users) with browser or RestClient(a plugin for rest webservice test).
Remember that you must put app id and app key in the request header. You can define the id and key in authority.properties.

========
###Reference:
Jersey:
* [Jersey](https://jersey.java.net/)
* [Jersey GitHub](https://github.com/jersey/jersey)
