Grocery Shop
This is the backend code for functionality of a simple e-commerce grocery shop. There are 3 main items you can buy: Bread, Beer and Vegetables. There are default
prices but you can change them, as well. We are a cool shop, so we have all the time discounts :) We start with default discounts. Once the project is complete
you would be able to change the values of those through an API call. For changing the logic of the discounts, need to quickly write a back end function. Here you wiil 
find mainly the logic and API calls. You can place the order and automatically will receive the receipt: how much you need to pay, discounts if applicable are applied.
No payment method solutions added.

API call example test code
curl -X POST http://localhost:4242/grocery-shop/generate-receipt -H "Content-Type: application/json" -H "Accept: application/json" -d'{ "items": [ { "name": "bread", "price": 1.00, "quantity": 5, "age": 5 }, { "name": "vegetable", "price": 1.00, "quantity": 500, "weight_for_price": 100, "unit": "g" }, { "name": "beer", "price": 0.50, "quantity": 8, "type": "Dutch", "unit": "bottle" } ] }'

curl -X POST http://localhost:4242/grocery-shop/generate-receipt -H "Content-Type: application/json" -H "Accept: application/json" -d '{ "items": []}'

curl -X GET http://localhost:4242/grocery-shop/test

curl -X POST http://localhost:4242/discount/change_beer -H "Content-Type: application/json" -d '{ "discountValues": { "BELGIUM_DISCOUNT": 3.5, "DUTCH_DISCOUNT": 2.5, "GERMAN_DISCOUNT": 4.5 }, "bottlesPerPack": 8 }'

curl -X POST http://localhost:8080/discount/change_bread -H "Content-Type: application/json" -d '{ "DAYS_SMALL_DISCOUNT": 2, "DAYS_BIG_DISCOUNT": 5, "SMALL_NUM_DEAL": 3, "BIG_NUM_DEAL": 4 }'

curl -X POST http://localhost:8080/discount/change_vegetable -H "Content-Type: application/json" -d '{ "SMALL_DISCOUNT": 0.06, "MEDIUM_DISCOUNT": 0.08, "BIG_DISCOUNT": 0.12 }'