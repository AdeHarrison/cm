# cake-manager2

# Overview

This service provides a range of Restful endpoints that enable -

* Retrieval of Cakes in JSON format
* Production of a downloadable JSON file of Cakes
* Manual creation of Cakes

##Please Note: All commands below must be run from the project root directory

# Endpoints

## Retrieve Cakes: `GET /`
### Example Json response:
```json
[
    {
        "title": "Lemon cheesecake",
        "description": "A cheesecake made of lemon",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
    },
    {
        "title": "victoria sponge",
        "description": "sponge with jam",
        "image": "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"
    }
]
```

## Produce a downloadable file: `GET /cakes`

## Create Cake: `POST /cakes` 
###With Swagger
[Click here with this service running](http://localhost:8281/swagger-ui.html#!/cake45endpoint/createCakeUsingPOST)
####enter Cake JSON data and click "Try it out"

###With curl
####e.g

curl -X POST -H "Content-Type: application/json" -d '{"title": "title 1", "description": "description 1", "image": "image 1"}' http://localhost:8281/cakes/

#Building the service
## mvn install
* This performs full build running all Unit/Integration tests and builds a new local docker image

#Running the service
##mvn spring-boot:run

#Building a docker image
##mvn spring-boot:build-image

#Running the docker image
##docker run -p 8281:8281 cake-manager2:1.0.0

# Running inside IntelliJ
###Right-click on the CakeManager2Application class
###Select Run or Debug option

# Testing

## To run Unit tests:
###mvn test

## To run Integration tests:
###mvn verify
