3 entities:
-Customer (id, firstName, lastName, addresses, accounts)
-Account (cardNumber, nameOnAccount, expirationDate)
-Address (line1, line2, countryCode)

MongoDb has 1 collection: Customer.

Service operations:
-Create customer
-Update customer
-Find customer(s) by: id, firstName and lastName, address, cardNumber.
-Find customer(s) with expired cards. (expirationDate before now)

Integration tests implemented using embedded MongoDb.
