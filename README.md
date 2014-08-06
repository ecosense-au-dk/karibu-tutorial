Karibu
===============

*Welcoming any data, in any format, in any version, at any time...*

Karibu is a high quality data collection framework aiming at data
integrity, flexibility, and scalability. It is developed for
environmental data collection from smartphone apps as well as
stationary sensors, and presently used as the backbone for several
research projects on-going at Computer Science, University of Aarhus.

Karibu consists of a client library that can be deployed on a Java
enabled device (Android phones for instance), and a server side daemon
responsible for storing incoming data. In addition, a Karibu system
must have a messaging system (RabbitMQ) and a database (MongoDB)
deployed.

Getting Started
----

To get started on the tutorials, you will need Java 1.7+, Ant 1.8+,
Ivy 2.4+, and to simulate the distributed environment using the
provided virtual machines you will need a way to run VMWare virtual
machines, such as
[VMWare Player](http://www.vmware.com/go/downloadplayer/) for Windows
and Linux, or
[VMWare Fusion](http://www.vmware.com/products/fusion/) for OS X.


Learning to use Karibu
---

The [Hello World](helloworld.md) tutorial takes you through the steps
of adapting Karibu for your particular domain and type of data
collection.

The [Quick Start](quickstart.md) tutorial focuses on setting up a
simulated environment that contains the core aspects of a full
production systems.

