# Item Repository Spring Boot Application #

### Gradle Commands ###

* ./gradlew clean test
* ./gradlew clean build
* ./gradlew run

### Curl commands ###

#### Movie Endpoints ####

* get all movies 
    * curl http:/localhost:9443/app/api/1.0/movies
    
* get movie by id 
    * curl -X GET http://localhost:9443/app/api/1.0/movies/get/2
    
* delete movie by id 
    * curl -X DELETE http://localhost:9443/app/api/1.0/movies/delete/1
    
* get all movies from year    
    * curl -X GET http://localhost:9443/app/api/1.0/movies/get/year?year=2019
    
* get all movies from director
    * curl -X GET http://localhost:9443/app/api/1.0/movies/get/director?director=jordan%20peele  
 
* get all movies from rating
    * curl -X GET http://localhost:9443/app/api/1.0/movies/get/rating?rating=r     
 
* get all descriptions from a movie id
    * curl -X GET http://localhost:9443/app/api/1.0/movies/get/descriptions/3
    
* create a new movie 
    * curl --header "Content-Type: application/json" \
            --request POST \
            --data '{"title":"my new title","rating":"PG-13","genre":"Action","director":"john doe","year":"2019"}' \
             http://localhost:9443/app/api/1.0/movies/add
     
* change the title of an existing movie
    * curl --header "Content-Type: application/json" \
                    --request PUT \
                   --data '{"title":"My new Title"}' \
                    http://localhost:9443/app/api/1.0/movies/put/1
