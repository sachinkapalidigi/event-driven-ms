### Run command from docker compose folder
>docker-compose -f common.yml -f kafka_cluster.yml up

### kcat utility - formerly kafkacat
[kcat doc](https://docs.confluent.io/platform/current/app-development/kafkacat-usage.html#kcat-formerly-kafkacat-utility)

Start the kcat container by including it in the host machine's network
>docker run -it --network=host confluentinc/cp-kafkacat kafkacat -L -b localhost:19092

L - list
b - broker
>kafkacat -L -b localhost:19092

>brew install kcat

>kcat -L -b localhost:19092

**Avro** - dependency for data communication in the kafka world
Strict schema and uses efficient byte serialization.
No need to keep names in json. 
Also has a direct mapping to and from JSON. 
It is more compact and faster for data communication. 
Preferred in the kafka world. 

Avro maven plugin
Source and destination files are mentioned in kafka-model/src/main/resources
This is referenced in pom.xml of the kafka-model
From this folder run in terminal `mvn clean install`
This will automatically generate Schema in destination folder mentioned in the pom.xml file.


**AdminClient Class** 
- From Apache kafka library
- Supports managing and inspecting topics, brokers, configuration and ACLs. 
- Minor version change can have breaking changes

Spring boot web flux
- Spring framework/web/reactive - WebClient
- WebClient
  - Nonblocking reactive client to perform HTTP requests, exposing a fluent, reactive API over underlying HTTP client libraries such as Reactor Netty
  - use create() or create(String) or builder() to prepare an instance

**Kafka producer**

- key serializer is long serializer
- value serializer used here is avro - which is used for modelling
- compression type is snappy, by google better than gzip and other compressions
- acks: used here is all , all replicas need to acknowledge
- 16KB batch size with batch boost factor to increase throughput
- linger ms will add a delay for light loads on producer side before sending records. Also increases throughput. 
- retry in case of error


***bootstrap.yml will run before application.yml***

Config Server will pull the data from config-server-repository.
The cloud config name in the bootstrap.yml of twitter-to-kafka service is important as config server repository has the .yml file which has prefix config-client and name similar to the one mentioned here. 
Before running mvn install from twitter to kafka service, config server needs to run as that is a requirement.

Spring cloud config server has spring security as dependency.
The username and password for accessing this is added in bootstrap.yml.
Twitter to kafka service should have the same name and password set under config to access the config server.

To use JCE - and encrypt passwords, download a Spring boot command line utility.
> sdkman.io/install

Use this utility to encrypt passwords.

Or can use Brew For MAC, instead of sdkman(common for all OS).

> sdk install springboot
 
or

> brew tap pivotal/tap
> brew install springboot

TO check - go to ~/.sdkman/candidates. You will find springboot here

> spring install org.springframework.cloud:spring-cloud-cli:3.1.1

> spring encrypt 'password_to_encrypt' --key 'secret_key_to_encrypt_password'

It uses random salt to encrypt, therefore each time the result will be different.

Keep the key used as an environment variable 
export ENCRYPT_KEY=secret_key_to_encrypt_password
Add it to bash/zsh profile so that it is available in the terminal.
could add to bootstrap.yml file as 
```bootstrap.yml
encrypt:
  key: secret_key_to_encrypt_password
```
  This is not recommended as this is openly present in yml file.

Spring boot config servers have endpoints to encrypt and decrypt data.

Make these routes public(remove authentication) to simplify usage.

Skip tests and install packages
> mvn clean install -DskipTests

