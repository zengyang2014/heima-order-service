# 订单支付请求
URL: `/orders/{orderId}/payment-request`
Method: `POST`
Request: 
```json
{
  "transactionId": "String",
  "orderId": "UUID"
}
```
Response 200: 
```json
{
  "orderId": "UUID",
  "amount": "double",
  "createAt": "DateTime",
  "status": "Enum (completed, cancelled, paid, wait for payment, accepted, ready for delivery)",
  "orderItems": [{
    "productId": "int", 
    "productName": "string", 
    "productionDescription": "string", 
    "numbers": "int", 
    "price": "double"
  }]
}
```
Response 400: 
```json
"Amount audit error"
```

# 查询所有订单
URL: `/orders`
Method: `GET`
Response 200: 
```json
[{
  "orderId": "UUID",
  "amount": "double",
  "createAt": "DateTime",
  "status": "Enum (completed, cancelled, paid, wait for payment, accepted, ready for delivery)",
  "orderItems": [{
    "productId": "int", 
    "productName": "string", 
    "productionDescription": "string", 
    "numbers": "int", 
    "price": "double"
  }]
}]
```

# 查询单个订单
URL: `/orders/{orderId}`
Method: `GET`
Response 200: 
```json
{
  "orderId": "UUID",
  "amount": "double",
  "createAt": "DateTime",
  "status": "Enum (completed, cancelled, paid, wait for payment, accepted, ready for delivery)",
  "orderItems": [{
    "productId": "int", 
    "productName": "string", 
    "productionDescription": "string", 
    "numbers": "int", 
    "price": "double"
  }]
}
```

# 取消订单
URL: `/orders/{orderId}/cancellation-request`
Method: `POST`
Response 201

# 创建订单
URL: `/orders`
Method: `POST`
Request: 
```json
{
  "amount": "double",
  "createAt": "DateTime",
  "orderItems": [{
    "productId": "int", 
    "productName": "string", 
    "productionDescription": "string", 
    "numbers": "int", 
    "price": "double"
  }]
}
```
Response 200: 
```json
{
  "orderId": "UUID",
  "amount": "double",
  "createAt": "DateTime",
  "status": "Enum (completed, cancelled, paid, wait for payment, accepted, ready for delivery)",
  "orderItems": [{
    "productId": "int", 
    "productName": "string", 
    "productionDescription": "string", 
    "numbers": "int", 
    "price": "double"
  }]
}
```

# 商家接受订单
URL: `/orders/{orderId}/acceptance-confirmation`
Method: `POST`
Response 200: 
```json
{
  "orderId": "UUID",
  "amount": "double",
  "createAt": "DateTime",
  "status": "Enum (completed, cancelled, paid, wait for payment, accepted, ready for delivery)",
  "orderItems": [{
    "productId": "int", 
    "productName": "string", 
    "productionDescription": "string", 
    "numbers": "int", 
    "price": "double"
  }]
}
```

# 商家备餐确认
URL: `/orders/{orderId}/acceptance-confirmation`
Method: `POST`
Response 200: 
```json
{
  "orderId": "UUID",
  "amount": "double",
  "createAt": "DateTime",
  "status": "Enum (completed, cancelled, paid, wait for payment, accepted, ready for delivery)",
  "orderItems": [{
    "productId": "int", 
    "productName": "string", 
    "productionDescription": "string", 
    "numbers": "int", 
    "price": "double"
  }]
}
```


# 用户收餐确认
URL: ` /orders/{orderId}/delivery-confirmation`
Method: `POST`
Response 200: 
```json
{
  "orderId": "UUID",
  "amount": "double",
  "createAt": "DateTime",
  "status": "Enum (completed, cancelled, paid, wait for payment, accepted, ready for delivery)",
  "orderItems": [{
    "productId": "int", 
    "productName": "string", 
    "productionDescription": "string", 
    "numbers": "int", 
    "price": "double"
  }]
}
```
