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
for personal use. You can find and download it from VMWare's website.

Once installed, download the two VMs (about 400MB each):

  * [Duma MQ](http://users-cs.au.dk/baerbak/c/vm/Duma-RSA-RabbitMQ.zip)  

  * [Duma DB](http://users-cs.au.dk/baerbak/c/vm/Duma-RSA-MongoDB.zip)

Unzip them in some suitable place. Start you VMWare player, select
*Open a Virtual Machine* and select the VM. 

![VMWare Player](resource/vm-start.gif)

The virtual machines are some that I use in my teaching and both have
username 'rsa' and password 'csau'.

### Get the MQ node running

Next request to execute it. If asked, select that you have copied
it. Other questions are usually regarding peripherical devices (skip
them) or VMWare tools (skip that as well). 

Log onto the machine, and note its IP (which we will call *mqip* in
the rest of the tutorial.)

![Duma MQ IP](resource/duma-1.gif)

You now have a RabbitMQ running, and can log into its dashboard using
your web browser - enter the URL *mqip:15672*. Log in using
name _guest_ and password _guest_ to get to the dashboard.

![Duma MQ dashboard](resource/mq-dashboard.gif)

### Get the MongoDB node running

Repeat the very same procedure to start the Duma-DB machine and note
its IP (*dbip*). 

Start the daemon
---

The daemon fetches messages from the MQ, deserializes the binary
payload into a MongoDB document, and stores it in the MongoDB.

Therefore the daemon must of course be configured to talk with both
RabbitMQ and MongoDB. It does so using property files.

In the root of this tutorial you will find the `config` folder. Open
your favorite editor on the `exchange.properties` file and enter your
*mqip* as value to the key `serverAddressList`. The rest of the
properties should do just well.

![Exchange properties](resource/exchange.gif)

Do the same with the `mongo.properties` file - change the
`serverAddressList`s value to the *dbip* you noted from the virtual
machine.

![Mongo properties](resource/mongo.gif)

OK, everything should be set. Let us start the daemon.

    ant daemon

and you should see something along the lines of (on Win7 some
connections take a while to start).

![Daemon start](resource/daemon-start.gif)

(if you wonder why the logging information is going to the shell, it
is because the tutorial's ivy.xml file uses the simple SLF4J
logger. If you change the binding to the Log4J logger, logging should
go to `karibu.log` instead, which is what we do in production.)

If this step fail, carefully review that you have set the IP addresses
correctly in the property files.

To stop the daemon, you just hit Ctrl-C.

Start data collection
---

To simulate data collection from a device, you should start the load
generator from another shell.

     ant load

This will start uploading data every second to the MQ which will then
be fetched by the daemons and stored in the DB.

To validate, please check the MQ dashboard.

![MQ upload](resource/mq-upload.gif)

Here you see that the publish and deliver rates are around 1 message
per second as expected. You can also click on the fanes
for`*Connections*, *Exchanges*, and *Queues*, to see the different
connections and objects that the daemon has defined.


To validate that data is indeed stored in the DB, find your Duma-DB
virtual machine, and start the Mongo shell, and execute the following
commands:

    use karibu
    show collections
    db.EXMRE001.count()

![Mongo upload](resource/mongo-upload.gif)

Here you see that 77 documents have been stored in the EXMRE001
collection in the karibu database. That data ends in the karibu
database was defined in the `mongo.properties` configuration; and the
collection is defined by the *producer code* as explained in the
[Hello World](helloworld.md) tutorial.

Code Walkthrough
---

Pending...

Experiments
---

You can try to keep the load going but shut down the daemon.

You can try to stop the MongoDB for a short while (`sudo service
mongodb stop`) before reenabling it (`sudo service mongodb start`).

