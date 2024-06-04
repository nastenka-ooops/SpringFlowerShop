# SpringFlowerShop
An online flower shop store application. It is a sample online store that allows customers to browse, order, and manage flower inventory. 

## Docunentation
- [product endpoints](https://github.com/nastenka-ooops/SpringFlowerShop/blob/main/documentation/endpointsForProduct.txt)
- [inventory endpoints](https://github.com/nastenka-ooops/SpringFlowerShop/blob/main/documentation/endpointsInventory.txt)
- [customer endpoints](https://github.com/nastenka-ooops/SpringFlowerShop/blob/main/documentation/endpointsCustomer.txt)
- [order endpoints](https://github.com/nastenka-ooops/SpringFlowerShop/blob/main/documentation/endpointsOrder.txt)
- [orderItem endpoints](https://github.com/nastenka-ooops/SpringFlowerShop/blob/main/documentation/endpointsOrderItem.txt)

## Tests
- [how to run tests](https://github.com/nastenka-ooops/SpringFlowerShop/blob/main/howToRunTests)

## Docker
If you are using Docker, open Terminal, type command and run: 

_docker run --network spring-flower-shop-net --name spring-flower-shop-container -e DB_URL=your-database-url(omit 'jdbc:' part of URL) -e USERNAME=your-database-username -e PASSWORD=your-database-password -p {your-port-to-run-app}:8080 spring-flower-shop_

## Built With

[Spring Boot](https://spring.io/projects/spring-boot/) - Server framewor

[Maven](https://maven.apache.org) - Build and dependency management

[MySQL](https://www.mysql.com) - Database

## Author
[Anastasiya Brutskaya](https://github.com/nastenka-ooops)
