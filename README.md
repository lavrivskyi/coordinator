# Coordinator
<p align="center">
<img src="https://user-images.githubusercontent.com/85931447/140752972-6d3b6977-d2dc-4245-9b89-684b7b4317ca.png" alt="Coordinator">
</p>

## Description
This project is a simple realization of geocoding service<br/>
The project is N-tier application with:
- DAO layer for interaction with DB.
- Service layer with business logic and third party API calls.
- Controller level for handling HTTP requests.<br/>

## Features
- Retrieving address by coordinates (latitude & longitude)
- Storing coordinates in H2 DB
- Retrieving addresses by coordinates stored in DB
- Caching retrieved coordinates and addresses
- Authentication for result endpoints
- Application dockerization

## Technologies used
- Java 8
- Spring Boot
- Spring Security
- H2 Database
- Maven
- Apache HttpClient
- Project Lombok
- Caffeine Cache
- Jackson Project
- Docker

## How to run locally
1. Signup on <a href="https://hub.docker.com/">Docker Hub</a>
2. Install <a href="https://docs.docker.com/get-docker/">Docker</a>
3. Run ```docker pull lavrivskyi/coordinator-lookup``` in the terminal
4. Open http://localhost:8080 in your browser

## REST API
The REST API to the Coordinator app is described below.
### GET coordinates by address
`http://localhost:8080/coordinates?street=<STREET NAME>&houseNumber=<BUILDING NUMBER>&city=<CITY>`
### Sample response
```json
[
  {
    "id": 1,
    "longitude": "30.5116423",
    "latitude": "50.4668952"
  },
  {
    "id": 2,
    "longitude": "30.51178804319849",
    "latitude": "50.4667641"
  },
  {
    "id": 3,
    "longitude": "30.511863",
    "latitude": "50.4667685"
  }
]
```
### GET addresses from DB
`http://localhost:8080/addresses`
### Sample response
```json
[
    {
        "address": {
            "shop": "Gulliver",
            "house_number": "1а",
            "road": "Sportyvna Square",
            "quarter": "Бессарабка",
            "suburb": "Клов",
            "borough": "Pecherskyi district",
            "city": "Kyiv",
            "postcode": "01023",
            "country": "Ukraine",
            "country_code": "ua"
        }
    },
    {
        "address": {
            "shop": "Gulliver",
            "house_number": "1а",
            "road": "Sportyvna Square",
            "quarter": "Бессарабка",
            "suburb": "Клов",
            "borough": "Pecherskyi district",
            "city": "Kyiv",
            "postcode": "01023",
            "country": "Ukraine",
            "country_code": "ua"
        }
    },
    {
        "address": {
            "amenity": "Unison",
            "road": "Sportyvna Square",
            "quarter": "Бессарабка",
            "suburb": "Клов",
            "borough": "Pecherskyi district",
            "city": "Kyiv",
            "postcode": "01023",
            "country": "Ukraine",
            "country_code": "ua"
        }
    }
]
```