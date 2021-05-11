# cake-manager2

# Overview

This service provides a range of Restful endpoints that enable -

* Retrieval of Cakes in JSON format
* Production of a downloadable JSON file of Cakes
* Manual creation of Cakes

### Please Note:

#### The app runs on port 8182 e.g localhost:8182

#### All commands below must be run from the project root directory

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

I've created a test oauth account in Okta purely for the purposes of this test, to run the command below -

* open https://oidcdebugger.com/
* enter https://dev-8627717.okta.com/oauth2/default/v1/authorize in "Authorize URI"
* enter https://oidcdebugger.com/debug in "Redirect URI"
* enter 0oapyswlxsGd9HK7L5d6 in "Client ID"
* enter {} in "state"
* Check "token" checked in "Response Type"
* Click "Send Request"
* Login using posting1@tutanota.com / cm2-test
* Copy token to clipboard

### With Swagger

* [Click here with this service running](http://localhost:8281/swagger-ui.html#!/cake45endpoint/createCakeUsingPOST)

* Paste token into the "Authorization" field

* enter Cake JSON data and click "Try it out"

### With curl

* Paste token into {paste_token_here} below and run

* curl -X POST -H "Content-Type: application/json" -H "Authorization: Bearer {paste_token_here}" -d '{"title": "title 1"
  , "description": "description 1", "image": "image 1"}' http://localhost:8281/cakes/

Please excuse the flagrant security breaches...

# Building the service

## mvn install

* This performs full build running all Unit/Integration tests and builds a new local docker image

# Running the service

## mvn spring-boot:run

* Enter localhost:8182 in Browser

# Building a docker image

## mvn spring-boot:build-image -DskipTests

# Running the docker image

## docker run -p 8281:8281 cake-manager2:1.0.0

# Running inside IntelliJ

### Right-click on the CakeManager2Application class

### Select Run or Debug option

# Testing

## To run Unit tests:

### mvn test

## To run Integration tests:

### mvn verify

## Common exposed Actuator Endpoints:

[Info](http://localhost:8281/actuator/info)

[Health](http://localhost:8281/actuator/health)

[Metrics](http://localhost:8281/actuator/metrics)

[All available](http://localhost:8281/actuator)

