Grocery Shop
This is the backend code for functionality of a simple e-commerce grocery shop. There are 3 main items you can buy: Bread, Beer and Vegetables. There are default prices but you can change them, as well. We are a cool shop, so we have all the time discounts :) We start with default discounts. The admin can change the values of those through an API call. For changing the logic of the discounts, need to quickly write a back end function. 
You can place the order via http req. and will receive the receipt: how much you need to pay, discounts if applicable are applied.
Planed: create the website(frontend) and database: store tables and data, retrieve via http calls
Security: create account, authenticate, allocate roles, only loggedin user with certain role can do certain operations.

                    ** API call example commands **

curl -X GET http://localhost:4242/grocery-shop/test

curl -X GET http://localhost:4242/grocery-shop/discount-rules

curl -X GET http://localhost:4242/grocery-shop/prices

                    ** CALLS FOR RECEIPTS/CURRENT DIFFERENT SCENARIOS **

// BREAD: AGE 6
curl -X POST http://localhost:4242/grocery-shop/receipts/current -H "Content-Type: application/json" -H "Accept: application/json" -d'{ "items": [ { "name": "bread", "price": 1.00, "quantity": 10, "age": 6 }, { "name": "vegetable", "price": 1.00, "quantity": 500, "weight_for_price": 100, "unit": "g" }, { "name": "beer", "price": 0.50, "quantity": 8, "type": "Dutch", "unit": "bottle" } ] }'
/* expected output:
Receipt:
1.) 10 x bread €10,00
Discount: €6,00
2.) 500 g x vegetable €5,00
Discount: €0,35
3.) 8 bottles x beer €4,00
Discount: €2,00
Total after discount: €10,65
*/

// BREAD AGE 3
curl -X POST http://localhost:4242/grocery-shop/receipts/current -H "Content-Type: application/json" -H "Accept: application/json" -d'{ "items": [ { "name": "bread", "price": 1.00, "quantity": 5, "age": 3 }, { "name": "vegetable", "price": 1.00, "quantity": 1000, "weight_for_price": 100, "unit": "g" }] }'
/* expected output:
Receipt:
1.) 5 x bread €5,00
Discount: €2,00
2.) 1000 g x vegetable €10,00
Discount: €1,00
Total after discount: €12,00

*/

// BREAD AGE 2 NO DISCOUNT
curl -X POST http://localhost:4242/grocery-shop/receipts/current -H "Content-Type: application/json" -H "Accept: application/json" -d'{ "items": [ { "name": "bread", "price": 1.00, "quantity": 5, "age": 2 }, { "name": "vegetable", "price": 1.00, "quantity": 1000, "weight_for_price": 100, "unit": "g" }] }'
/* expected output:
Receipt:
1.) 5 x bread €5,00
Discount: €0,00
2.) 1000 g x vegetable €10,00
Discount: €1,00
Total after discount: €14,00
*/

curl -X POST http://localhost:4242/grocery-shop/receipts/current \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{ "items": [ { "name": "bread", "price": 1.00, "quantity": 5, "age": 7 }, { "name": "vegetable", "price": 1.00, "quantity": 500, "weight_for_price": 100, "unit": "g" }] }'
// output: The bread you entered is too old to be sold (age 7 days).

curl -X POST http://localhost:4242/grocery-shop/receipts/current \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{ "items": [] }'
// No items were entered in the receipt

curl -X POST http://localhost:4242/grocery-shop/receipts/current \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{}'
// No items were entered in the receipt

                    ** CALLS FOR /CHANGE-DISCOUNT/ITEM changes default values for discounts **

curl -X POST http://localhost:4242/grocery-shop/change-discount/beer -H "Content-Type: application/json" -d '{
"belgiumDiscount": 3.5,
"dutchDiscount": 2.5,
"germanDiscount": 4.5,
"bottlesPerPack": 4
}'

curl -X POST http://localhost:4242/grocery-shop/change-discount/bread -H "Content-Type: application/json" -d '{
"daysSmallDiscount": 2,
"daysBigDiscount": 4,
"smallNumDeal": 1,
"bigNumDeal": 2
}'
//output: Bread( 2 - 3 days old): buy 1 get 1; (4 days old): buy 1 get 2;

curl -X POST http://localhost:4242/grocery-shop/change-discount/vegetable -H "Content-Type: application/json" -d '{
"smallDiscount": 0.03,
"mediumDiscount": 0.06,
"bigDiscount": 0.15
}'
// output: Vegetables(1 - 100g): 3.0%; (101 - 500g): 6.0%; Over 500g: 15.0%;
