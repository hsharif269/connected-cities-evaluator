# connected-cities-evaluator-microservice
MVP (Minimum Viable Product) Version of Microservice to check if two cities are connected togather

## Technologies Used

* Java 1.8
* Spring Boot
* JUnit, Mockito
* Spring Tool Suite
* Swagger 2

## Run Instructions

### Prerequisites

Clone the repository or download zip.

Executable connected-cities-evaluator-1.0.jar is located in connected-cities-evaluator/target/ directory.

### Run Option 1: Web Browser

Start the application by issuing the following command (from the directory where the above jar is located):

java -jar connected-cities-evaluator-1.0.jar ConnectedCitiesEvaluatorApplication.class

Open a browser and try the following API calls:

http://localhost:8080/connected?origin=Boston&destination=Newark
(Should return yes)

http://localhost:8080/connected?origin=Boston&destination=Philadelphia
(Should return yes)

http://localhost:8080/connected?origin=Philadelphia&destination=Albany
(Should return no)

### Run Option 2: Swagger UI

Start the application as described in Run Option 1.

Open a browser and go to http://localhost:8080/swagger-ui.html.

On the API documentation page, click on the arrow next to "connected-cities-evaluator-controller".

Click on "GET /connected".

Click on "Try it out" button.

Provide origin and destination city names.

Click on "Execute".

### Run Option 3: Postman

Start the application as described in Run Option 1.

Send GET requests on Postman using the URLs as above.


## Assumptions/Decisions Made

 If an identical city name is provided for both origin and destination, "yes" will be returned (even if the city does not appear in the input file).

## Approach to Problem

Build a cities graph where each city becomes a node and a direct path between two cities becomes an edge. 

In order to detect a connection between two given cities, use the following algorithm:

1. Check base/edge cases:

   a. If the two cities are identical, i.e., represent the same city, return true.

   b. If either of the two cities is not a node in the cities graph, return false.

   c. If the two cities are directly connected, i.e., there is an edge between the two cities, return true.

2. Use BFS to check if the given two cities are connected:

   Initially, the queue and visited set contain one of the two cities, regarded as the origin city.

   While the queue is not empty, poll a city from the queue, and check each of the adjacent cities directly connected.
   
   		a. If an adjacent city is the destination city, return true.
   	
   		b. Else, if the adjacent city has not been visited yet, mark it as visited and add it to the queue.

   Once the queue is emptied out, it means that there is no connection between the two input cities, so return false.
  
