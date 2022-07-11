3 entities:
-Customer (id, firstName, lastName, addresses, accounts)
-Account (numberOfCard, name, expireDate)
-Address (line1, line2, codeOfCountry)

1 MongoDb collection: Customer.

Service operations:
-Create customer
-Update customer
-Find customer(s) by: id, firstName and lastName, address, numberOfCard.
-Find customer(s) with cards that are expired.

Integration tests implemented using embedded MongoDb.

Jenkins job test
