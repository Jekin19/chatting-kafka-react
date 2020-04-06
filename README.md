# `<Chatting Application>`

# Service overview

A chat application that sends and receives messages between A Client and B Client.

![Chatting Sample](https://user-images.githubusercontent.com/12117401/78600991-f81b1600-7821-11ea-9a34-2749cff5eaae.png)

# Development scope

- A User and b user send and receive messages 1: 1.
- A user can send a single file to another user.
- The client uses a web browser. It is a kind of web-based chatting program.
- It has a message queue. A When a user sends a message, it pushes it to a message queue and then pulls this message and sends it to the another user.

# Building

Step 1: Download the code https://www.apache.org/dyn/closer.cgi?path=/kafka/2.4.1/kafka_2.12-2.4.1.tgz
Download the 2.4.1 release and un-tar it.

> tar -xzf kafka_2.12-2.4.1.tgz
> cd kafka_2.12-2.4.1

Step 2: Start the server
Kafka uses ZooKeeper so you need to first start a ZooKeeper server if you don't already have one. You can use the convenience script packaged with kafka to get a quick-and-dirty single-node ZooKeeper instance.

> bin/zookeeper-server-start.sh config/zookeeper.properties
> [2013-04-22 15:01:37,495] INFO Reading configuration from: config/zookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)

> bin/kafka-server-start.sh config/server.properties
> [2013-04-22 15:01:47,028] INFO Verifying properties (kafka.utils.VerifiableProperties)
> [2013-04-22 15:01:47,051] INFO Property socket.send.buffer.bytes is overridden to 1048576 (kafka.utils.VerifiableProperties)
> ...

Step3: Start the Kafka Springboot application
Go to kafka-server folder and follow the Readme instructions

- This will start the server on http://localhost:8080

Step4: Start the React client application
Go to react-client folder and follow the Readme instructions

- This will start the application on http://localhost:3000
