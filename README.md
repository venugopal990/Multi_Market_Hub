# Multi-Market-Hub

MultiMarketHub makes selling easy! Manage your products effortlessly across many online stores, ensuring a smooth shopping experience for both sellers and buyers with inventory control and customized features.


## Admins API's
####  GET /admins - retrieves a list of all users  
####  GET /admins/{id} - retrieves a specific users by ID  
####  POST /admins - creates a new users (admin) 
####  PUT /admins/{id} - updates an existing users by ID 
####  DELETE /admins/{id} - deletes a users by ID 


## Stores API's
####  GET /stores - retrieves a list of all stores 
####  GET /stores/{Id} - retrieves a specific stores by ID 
####  POST /stores - creates a new stores 
#### PUT /stores/{Id} - updates an existing stores by ID
#### DELETE /stores/{Id} - deletes a stores by ID


## Products API's
#### GET /stores/{storeId}/products - retrieves a list of all products
#### GET /stores/{storeId}/products/{id} - retrieves a specific products by ID
#### POST /stores/{storeId}/products/- creates a new products
#### PUT /stores/{storeId}/products/{id} - updates an existing products by ID
#### DELETE /stores/{storeId}/products/{id} - deletes a products by ID

## Customers API's
#### GET /customers - retrieves a list of all users
#### GET /customers/{id} - retrieves a specific users by ID
#### POST /customers - creates a new users (admin)
#### POST /customers?storeId=? - creates a new users (customer) based on store id
#### PUT /customers/{id} - updates an existing users by ID
#### DELETE /customers/{id} - deletes a users by ID
