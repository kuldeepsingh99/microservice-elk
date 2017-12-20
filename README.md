# Creating microservice using Spring Cloud, Eureka, Zuul, Sleuth, Zipkin and ELK Stack

Spring framework provides set of libraries for creating microservices in Java.

* Zuul - gateway service that provides dynamic routing, monitoring, resiliency, security, and more
* Eureka - service registration and discovery
* Sleuth - distributed tracing via logs
* Zipkin - distributed tracing system with request visualization
* ELK Stack - We are using Logstash, Elastics Search and Kibana to index logs

## About Projects

1. **account** :- Its a microservice, spring boot and sleuth is used to implement the same
2. **product** :- its a microservice, spring boot and sleuth is used to implement the same
3. **apigateway** :- its a API Gateway, spring Zuul is used to implement the same
4. **eureka-service** :- its for service discovery and registration, spring Eureka is used to implement the same
5. **zipkin-service** :- its for log tracking, spring zipkin is used to implement the same

## Technologies/dependencies
1. Java 8
2. Spring boot, sleuth, zipkin, eureka
3. ELK - Elastics search, logstash and Kibana

## Why do we need ELK Stack

With use of microservices, we are able to create stable distributed applications. But it has also introduced few challenges in other areas e.g. distributed log management and ability to view logs of full transaction distributed among many services and distributed debugging in general.

As the number of microservice increases and we enable cloud deployment with automated continuous integration tools, it is very much necessary to have some provision of debugging the components when we have any problem, we have a tool called [Elastics Search](https://www.elastic.co/), [Logstash](https://www.elastic.co/products/logstash) and [Kibana](https://www.elastic.co/products/kibana) 


## ELK Stack

1. **Elastics Search** is a distributed, JSON-based search and analytics engine designed for horizontal scalability, maximum reliability, and easy management.
2. **Logstash** is a dynamic data collection pipeline with an extensible plugin ecosystem and strong Elasticsearch synergy.
3. **Kibana** gives the visualization of data through a UI.

## ELK Stack Architecture
![alt text](https://github.com/kuldeepsingh99/microservice-elk/blob/master/images/elastics.jpg "ELK")

## ELK Configuration

We will use Docker container to run the ELK Stack

1. Run this command on the docker terminal.
   * docker run -d -it --name es -p 9200:9200 -p 9300:9300 -e ES_JAVA_OPTS="-Xms1g -Xmx1g" -m 1500m elasticsearch
    
    This command will start elastics search container on 9200/9300 port
    
   * docker run -d -it --name kibanak --link es:elasticsearch -p 5601:5601 kibana
   
   This command will start Kibana on 5601 port and it will also link it with elastics search container
   
   * docker run -d -it --name logstash -p 5000:5000 logstash -e 'input { tcp { port => 5000 codec => "json" } } output { elasticsearch { hosts => ["192.168.99.100"] index => "micro-%{serviceName}"} }'
   
   This command will start Logstash container on 5000 port and it will also create an index with name micro-*
   
2. Checking with docker ps command, all their container should be running
![alt text](https://github.com/kuldeepsingh99/microservice-elk/blob/master/images/dockerps.PNG "ELK Stack")

## Creating Micro Service

### Spring Zuul, eureka flow
![alt text](https://github.com/kuldeepsingh99/microservice-elk/blob/master/images/ZuulFlow.png "Zuul flow")

### These are the default ports used for the services in this example
1. **account** :- 8080
2. **product** :- 9090
3. **apigateway** :- 8765
4. **eureka-service** :- 8761
5. **zipkin-service** :- 9411

### Download the the services and import it to Eclipse or any Id

Run all the services one by one and make sure all the services are running, you need to run **product** and **account** microservice with multiple ports e.g. spring-boot:run -Dserver.port=8080, spring-boot:run -Dserver.port=8081 

1. **Open the Eureka Service (its running on port 8761)**
   * Here we observed that two microservice (Account and Product) is registered with different ports
   ![alt text](https://github.com/kuldeepsingh99/microservice-elk/blob/master/images/eureka.png "Spring Eureka")
   
2. **Try to Access to access account service http://localhost:8765/api/account/greeting**
   * We will get response from Account Service
   
3. **Checking the log Traces on zipkin server http://localhost:9411/**
   * here we can check all the log traces, in our example account microservice calls product microservice, so we can check here that which serivice took how much time etc.
   ![alt text](https://github.com/kuldeepsingh99/microservice-elk/blob/master/images/zipkin.png "Spring Zipkin")
   
4. **Finally check logs on Kibana**
   * In Kibana we can check every log from our microserivices
   * with simple **Log.info** statement and **logback.xml** configuration we can index and view log on Kibana

logger.info(" inside getproductdetails  Micro service ");

please check logback.xml (in account and product) for more details

![alt text](https://github.com/kuldeepsingh99/microservice-elk/blob/master/images/kibana.png "Kibana")








