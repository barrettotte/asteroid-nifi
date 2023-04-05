# asteroid-nifi

A NiFi flow to ingest and transform asteroid data.

This little project is just to get more practice with data flows using Apache NiFi and Apache Kafka.
I'm using a combination of real data pulled from the [NASA NeoWs API](https://api.nasa.gov/) and generated random data.

TODO: diagram

## URLs

- Flask Asteroid Reporter - http://localhost:9000
- SpringBoot Asteroid API - http://localhost:9001
- SpringBoot Asteroid API Swagger - http://localhost:9001/swagger-ui.html
- NiFi - http://localhost:8250/nifi/
- TODO: Thymeleaf summary

## Dev

- Spin up everything - `docker compose up -d`
- Spin down everything - `docker compose down` (add `--volumes` to also wipe out volumes)
- Fully rebuild SpringBoot API - `docker compose up -d --force-recreate --build asteroid-sb`
- Send a random asteroid - `./scripts/push-asteroid.sh`
- Send 10 random asteroids - `./scripts/push-asteroid-many.sh`

## References

- NASA JPL NEO - https://cneos.jpl.nasa.gov/
- NASA API: Asteroids - NeoWs - https://api.nasa.gov/
