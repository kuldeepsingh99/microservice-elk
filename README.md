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

## Creating micro service






