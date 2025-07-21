Customer service section1 microservice implementation

## Endpoint: 
1. /api/customer/create
2. /api/customer/getData
3. /api/customer/getById/{customerId}
4. /api/customer/update
5. /api/customer/update/mobileNumber/{mobileNumber}
6. /api/customer/deleteById/{customerId}

## Http mapping method
1. PostMapping
2. GetMapping
3. GetMapping
4. UpdateMapping
5. PatchMapping
6. DeleteMapping

## sample create payload
``
{
    "customerId": 1001,
    "customerName": "sadakhat",
    "customerAddress": "Talupula",
    "customerMobileNumber": "7702272848"
}
``


