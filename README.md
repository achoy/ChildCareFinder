# ChildCareFinder
GEO:CODE 2017

## TO RUN

Pass in username and password of MongoDB instance
MongoDB instance is hardcoded
`scripts/buildrun.sh [username] [password]`

## API ROUTES

ENDPOINT: http://localhost:5000
ENDPOINT: http://childcarefinder.choyware.com:5000

### `/api/providers`

Return all providers with query

### `/api/providers?zip=zipcode`

Return all providers matching exact zip code

### `/api/providers?city=cityname

Return all providers matching city name

### `/api/provider/{id}`

Get or update a provider info (id = licenseNumber)

### `/file.html`

returns any  file.html