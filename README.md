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

Also, Ivy will need to download modules so you will have to be on-line
to make the tutorials work.


Learning to use Karibu
---

Using Karibu for your particular data collection needs requires two
important steps

  1) Configuring Karibu to handle your particular type(s) of
  data. This involves writing implementations of the `Serializer` and
  `Deserializer` interfaces and testing that they work correctly
  together with Karibu. Tutorial: [Hello World](helloworld.md).

  2) Setting up the distributed Karibu environment which includes
  RabbitMQ, MongoDB, Karibu daemon(s), and the clients. Tutorial:
  [Quick Start](quickstart.md).

As Karibu is designed with a lot of flexibility, these two tasks can
actually be made in any order.


The [Hello World](helloworld.md) tutorial focus on the architecture
and the code and configuration options of Karibu in order to develop
the binding to your particular types of data.

The [Quick Start](quickstart.md) tutorial focuses on setting up a
fully distributed environment that contains the core aspects of a full
production systems but demonstrates it on a provided, fixed, data
type.

