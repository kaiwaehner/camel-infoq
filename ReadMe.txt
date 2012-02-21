Apache Camel Introduction Project
=================================

This is an Eclipse project. Nevertheless, it can be used via console, or imported into NetBeans / IntelliJ IDEA. 

Libraries are imported via Maven to ease dependency management. 
You should always use the newest version of Camel before starting (current version: 2.9.0).

Run the UnitTest (IntegrationTest.java). Put a breakpoint into the debugger and start playing around.
If you do not understand the message flow, uncomment ".tracing()" in the route to get more detailed information.
You can also start the main method of CamelStarter.java and copy the resources/real_order.csv file to the inbox directory
(within 30 seconds) to see how it is processed. 

This is only a very simple example for getting started. In practice, you would have to do some stuff differently, of course.
For instance: If you have got two dvd orders, the last one overrides the first one in the outbox/dvd folder at the moment.

Have fun with Apache Camel.

Best regards,
Kai Waehner
Twitter: @KaiWaehner
www.kai-waehner.de


    