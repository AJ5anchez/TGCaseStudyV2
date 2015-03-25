# PopulatePricesDB
# Summary
Spring Boot code that implements a simple REST end-point that interacts with an external HTTP-based service
and a (remote) MongoDB document database.
# Building | Running | Testing
Once you have cloned this repository, go to the PopulatePricesDB directory, and do any/all of the following, which assumes you have maven [2] installed in your system:

(1) `mvn spring-boot:run`

This just runs the application, and therefore starts a Tomcat server locally, which
listens to HTTP requests at port 8080.

Currently, the only end-point exposed is:

http://localhost:8080/products/{pid}

where {pid} refers to a product identifier. This end-point uses the pid to
connect to a service, from which the name of the product with this id is
retrieved, and then it is aggregated with the current price of this product,
extracted from the MongoDB.

For example, the following request:

http://localhost:8080/products/15117729

produces the following response, represented in JSON as follows:

{
  "pid": 15117729,
  "name": "Apple® iPad Air 2 16GB Wi-Fi - Gold",
  "currentPrice": {
    "value": 35.99,
    "currencyCode": "USD"
  }
}

When the {pid} is not found in the service (no name found), or when it is not
found in the price document database (no price found), the respective 
exception is thrown, and clients can process these exceptions accordingly.

For instance, Spring Boot provides, by default, an error page that is shown
below for the following request sent from a browser:

http://localhost:8080/products/15643793

Whitelabel Error Page

This application has no explicit mapping for /error, so you are seeing this as a fallback.

Tue Mar 24 00:10:11 EDT 2015
There was an unexpected error (type=Not Found, status=404).
ERROR: Name not found for pid = 15643793

(2) `mvn clean package`

This does two things: (a) Runs the application and all tests in the project (currently only two test cases); and (b) Generates a jar file in the directory `target`. Once the jar file is generated, the application can be executed as:

`java -jar target/demo-1.2.2.jar`

and then the application behaves as described above in (1)

(3) `mvn -Dtest=TgCaseStudyV2ApplicationTests test`

This runs the application and then the test cases--but does not generate the jar file.

#Additional Notes
To create this small project, some of the references I consulted are:
- [https://spring.io/guides/gs/accessing-data-mongodb/](https://spring.io/guides/gs/accessing-data-mongodb/)
- [https://spring.io/guides/gs/accessing-mongodb-data-rest/](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
- [https://spring.io/guides/gs/consuming-rest/](https://spring.io/guides/gs/consuming-rest/)

#Known Issues:
- I used [RestTemplate](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) to automatically transfrom a GET response to a given object. It seems that the mappping components underpining RestTemplate map some characters to their HTML
code, which makes some cases to fail, even though the results are correct. I will try to fix this before
the discussion of this case study.
