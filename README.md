# Restaurant Order Service

This is the order service for restaurant. Given the lineItems POST-ed to the service, it returns the total of individual line items as well as the total of all line items. To get the price of individual line items, it calls the item-service and calculates the total.

#### Architecture

![Architecture](Diagram.png?raw=true "Architecture")

#### Request
```
POST http://localhost:11000/orders/type/takeaway
header: Content-Type:application/json

[
	{
		"itemId": 1001,
		"quantity": 2
	},
	{
		"itemId": 1003,
		"quantity": 1
	}
]
```

#### Response
```
{
    "lineItems": [
        {
            "itemId": 1001,
            "name": "Mutton Biriyani",
            "price": 220,
            "quantity": 2,
            "total": 440
        },
        {
            "itemId": 1003,
            "name": "Galauti Kabab",
            "price": 230,
            "quantity": 1,
            "total": 230
        }
    ],
    "total": 670
}
```

#### Order of services to start

```
1.0 Goto Zipkin and do docker-compose up
2.0 Start Config Server
3.0 STart Naming Server
4.1 Start Item Service1
4.2 Start Item Service2 (Optional for load balancing demo)
5.0 Start Order Service
6.0 Start API Gateway Server
 ```