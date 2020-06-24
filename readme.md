# Kafka-client sample
This is sample kafka client program, consist of producer and cosumer which transfer data using json object

To build sample kafka server install **docker** and visit [https://hub.docker.com/r/wurstmeister/kafka](https://hub.docker.com/r/wurstmeister/kafka)

create yaml file configuration **docker-compose.yml**

    version: '2'
    services:
      zookeeper:
        image: wurstmeister/zookeeper
        ports:
          - "2181:2181"
      kafka:
        image: wurstmeister/kafka:2.12-2.5.0
        ports:
          - "9092:9092"
        environment:
          KAFKA_ADVERTISED_HOST_NAME: 192.168.43.239
          KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
        volumes:
          - /var/run/docker.sock:/var/run/docker.sock

Change **KAFKA_ADVERTISED_HOST_NAME** to your host network ip, dont use localhost / 127.0.0.1.

Run kafka docker with docker-compose command:

    docker-compose -f docker-compose.yml up

To stop kafka docker run:

    docker-compose -f docker-compose.yml stop

After server was running, you can start run application with passing program args[0] value with **producer** (to start producer) or **consumer** (to start consumer)
