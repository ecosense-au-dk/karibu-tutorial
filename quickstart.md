Karibu Quick Start
============

Version 1 / July 2014

In this tutorial you will setup a distributed system having a client,
a messaging system, a daemon, and a database. Then you will start
sending data from the client through the Karibu system and see it
stored safely in the database.

Mise en place
----

This tutorial requires three nodes to be running: 

  1. your development machine will act both as a client as well as host
    the Karibu daemon

  2. a node that runs the RabbitMQ messaging system

  3. a node that runs the MongoDB database

To avoid the hazzle of setting up the latter two nodes, I recommend
downloading two Ubuntu Server 12.04 LTS virtual machines that are
already setup correctly.

The virtual machines are in VMWare format. They have been tested to
work with VMWare's virtual machine monitor VMWare Player which is free
for personal use. You can find and download from VMWare's website.

Once installed, download the two VMs.

  * [Duma MQ](http://users-cs.au.dk/baerbak/c/vm/Duma-RSA-RabbitMQ.zip)  

  * [Duma DB](http://users-cs.au.dk/baerbak/c/vm/Duma-RSA-MongoDB.zip)

The virtual machines are some that I use in my teaching and both have
username 'rsa' and password 'csau'.

Start the MQ machine and log into it, and note its IP. 

![Duma MQ IP](resource/duma-1.gif)

You now have a RabbitMQ running, and can log into its dashboard using
your web browser - provide its IP address on port 15672. Log in using
name _guest_ and password _guest_ to get to the dashboard.

![Duma MQ dashboard](resource/mq-dashboard.gif)

