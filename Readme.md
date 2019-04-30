# telemetry
The main idea is taken from https://towardsdatascience.com/lambda-architecture-how-to-build-a-big-data-pipeline-part-1-8b56075e83fe

Pipeline start:
1. Start Eureka server<br>
cd ./eureka-server<br>
mvn spring-boot:run
   
1. Start Zuul proxy<br>
cd ./zuul-server<br>
mvn spring-boot:run

1. Start Kafka for data transfer<br/>
cd ./KafkaAndCassandra<br/>
docker-compose up
   
1. Start data receiver instance<br>
cd ./data-receiver-service<br>
mvn spring-boot:run

1. Start data storage instance<br/>
cd ./data-storage-service<br/>
mvn spring-boot:run

1. Start data producer<br/>
cd ./data-producer<br/>
mvn exec:java

1. Check stats (secured area):<br/>
http://localhost:8762/data-storage-service/stats<br/>
User credentials:<br/>
username: admin<br/>
password: password

Here you can check
1. Load balancer and data receiver: http://localhost:8762/data-receiver-service/healthCheck
1. Eureka admin page: http://localhost:8761/

**Debugging kafka data flow**<br/>
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Telemetry --from-beginning<br/>
./kafka-console-producer.sh     --broker-list localhost:9092     --topic Telemetry