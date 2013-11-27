Going reactive (with the unfortunate tomcat7 and maven boilerplate)

This project shows as an example using akka cluster in tomcat7 with maven and scala to publish a message among all tomcat nodes.

Why with tomcat? Sometimes your system is already based on tomcat so you would not want akka as standalone (ie. the operations team already knows to monitor tomcat), in these cases you want akka on top of tomcat, if your system is also built on maven you also want your maven to be aware of akka (instead of using plain sbt).

Preparing your project for akka cluster and publish subscribe.

<dependency>
	<groupId>com.typesafe.akka</groupId>
	<artifactId>akka-contrib_2.10</artifactId>
	<version>2.1.0</version>
</dependency>

which will enable you to use import DistributedPubSubMediator class.

You need to have a class named ScalatraBootsrap at the root of your project (src/scala) scalatra will search for this file by default.
This file should initialize your actor system (as can be seen in example).

to run two nodes in the cluster see following configuration with intellij

![alt text 1](http://github.com/tomer-ben-david/akkaServlet/raw/master/doc/images/tomcat-akka-cluster-2552.png)

and for the second node:

![alt text 2](http://github.com/tomer-ben-david/akkaServlet/raw/master/doc/images/tomcat-akka-cluster-2552.png)


Call http://localhost:8080/submit

to sumbit from first node to both nodes (its a topic).

Call http://localhost:8081/submit

to submit from second node to both nodes..