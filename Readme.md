# telemetry

Pipeline start:
1. Start Eureka server<br>
cd ./eureka-server<br>
mvn spring-boot:run
   
1. Start Zuul proxy<br>
cd ./zuul-server<br>
mvn spring-boot:run
   
1. Start two data receiver instances<br>
cd ./data-receiver-service<br>
mvn spring-boot:run -Dserver.port=23486<br>
mvn spring-boot:run -Dserver.port=23487

1. Start Kafka and Cassandra for data transfer and storage
cd ./KafkaAndCassandra
docker-compose up

Here you can check
1. Load balancer: http://localhost:8762/data-receiver-service/greeting
1. Eureka admin page: http://localhost:8761/