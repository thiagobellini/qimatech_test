# Qimatech Application Test

Implemented a CRUD for products, with the following possibilities:
* POST - Register a new product
* GET - Search for all registered products
* GET - Search product by Id
* PUT - Update a product
* DELETE - Delete a product

\
In addition, unit tests and integration tests were implemented to ensure the system's effectiveness.

\
To use the system, Postman was made available, which can be found in a collection in the postman folder.

[Postman](postman)

\
\
The application uses Docker. To upload it, just have Docker installed on your machine, go into the root folder of the project and run the command:

```docker-compose up --build -d``