# ReviewEngineSpriongBoot
Demonstrate Review Engine Functionality 

Please read the document section to know about the components used.

API used:
1. Create User:

curl --location --request POST 'http://localhost:8080/api/v1/customer/create/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "first_name": "Test1",
    "last_name": "User",
    "email": "richabhuwania5@gmail.com"
}'

Response:

{
    "status": {
        "status_code": 200,
        "status_message": "Success",
        "status_type": "SUCCESS",
        "total_count": 1
    },
    "users": {
        "id": 5,
        "first_name": "Test1",
        "last_name": "User",
        "email": "richabhuwania5@gmail.com"
    }
}

2. Create Product:

curl --location --request POST 'http://localhost:8080/api/v1/product/create/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "product_name": "orange",
    "product_description": "fruit",
    "price": 50.50
}'

Response:

{
    "status": {
        "status_code": 200,
        "status_message": "Success",
        "status_type": "SUCCESS",
        "total_count": 1
    },
    "catalog_product": {
        "id": 4,
        "product_name": "orange",
        "product_description": "fruit",
        "price": 50.5,
        "product_reviews": null,
        "aggregated_score": null
    }
}

3. Create Review:

curl --location --request POST 'http://localhost:8080/api/v1/review/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "review_score" : 5,
    "comment": "Very Good",
    "user_id": 3,
    "product_id": 2
}'

Response:

{
    "status": {
        "status_code": 200,
        "status_message": "Success",
        "status_type": "SUCCESS",
        "total_count": 1
    },
    "product_review": {
        "id": 6,
        "review_score": 5.0,
        "comment": "Very Good",
        "user_id": 3,
        "product_id": 2
    }
}

4. Read product review:

curl --location --request GET 'http://localhost:8080/api/v1/product/get/2/'

Response:

{
    "status": {
        "status_code": 200,
        "status_message": "Success",
        "status_type": "SUCCESS",
        "total_count": 1
    },
    "catalog_product": {
        "id": 2,
        "product_name": "apple",
        "product_description": "fruit",
        "price": 50.5,
        "product_reviews": [
            {
                "id": 2,
                "review_score": 4.0,
                "comment": "Very Good",
                "user_id": 2,
                "product_id": 2
            },
            {
                "id": 5,
                "review_score": 5.0,
                "comment": "Very Good",
                "user_id": 2,
                "product_id": 2
            }
        ],
        "aggregated_score": 4.5
    }
}


Response: If the product is not found:

{
    "status": {
        "status_code": 400,
        "status_message": "Item not found",
        "status_type": "ERROR",
        "total_count": 0
    },
    "catalog_product": null
}
