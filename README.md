# grocery_shop3

An e-commerce grocery store has required you to implement an
application that calculates the order total and prints it's receipt
applying the following business rules:
● One order can have many items
● Items can only be breads, vegetables or beers
● Breads always have discounts like “buy 1 take 2”, or “buy 1
take 3”. If the bread is one day old or newer, it has no
discounts. If it's 3 days old it has the “buy 1 take 2” discount,
and if it's 6 days old, it's “pay 1 take 3”. Breads older than 6
days cannot be added to orders.
● Vegetables have a % based discount depending on the
weight. If you buy between 0g and 100g (always in the same
order), you have 5% of discount (applied to all vegetable
items), 7% between 100g and 500g, and 10% of fixed discount
if all vegetables weight more than 500g.
● Beers have only discounts if bought in packs containing 6
beers. The discount rules are fixed per pack:
● € 3,00 for each Belgium beer pack
● € 2,00 for each Dutch beer pack
● € 4,00 for each German beer pack
Single bottles/cans of beer can always be added to the order,
but in that case there is no discount. Buying 6 separate
bottles of the same beer is the same as buying one pack of
the same beer.

Instructions:
● Use at least Java 8 and whatever frameworks you like
● Use maven, bazel, or gradle to compile and run your unit tests
● Write a REST Service that contains the item discounts as described
above and has the following functionality
○ Given an Order, return the total cost with discounts applied,
and a receipt that breaks down the prices
○ List the current discount rules
○ List the current prices per item
● Make sure your code compiles and unit tests are passing
Requirements:
● Business rules correctness
● Your OO modelling approach
● Code readability
● Design patterns
● Quality of your unit tests
● Ability to customize prices, units and add new types of discounts
quickly
Example order w/ prices:
Bread €1,00, Veg €1,00 per 100g, Beer €0,50 per bottle
1. 3 x Bread (three days old) €2,00
2. 200g Vegetables €1,86
3. 6 x Dutch Beers €2,00
   Total: €5,86

   We want to see how you build services to support the
   business, so understanding how to organize an API for easy
   use and understanding is key. Any non-functional
   requirements you think are necessary for this type of service
   please add in.
   Please note that the contents of this document are
   confidential, and are proprietary to Payaut. Do not share or
   forward them to anyone.
   Do Don’t
   ● Keep it simple
   ● Write unit tests
   ● Service should start with
   default data
   ● Provide documentation
   where you think it
   necessary

