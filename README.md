# asteroid-nifi

A NiFi flow to ingest and transform fake Near-Earth Object (NEO) data based on the NASA JPL NEO database.

## TODO

Near earth objects - data model based on https://cneos.jpl.nasa.gov/

- Flask app with Jinja - generate random sets of NEOs in simple web page, save to MongoDB (NiFi pull from here)
- SpringBoot API - push directly to NiFi, CRUD on postgres
- Shell - push directly to NiFi
- Postgres - final resting place of data
- NiFi 
  - accepts input from SpringBoot and Shell
  - pulls data from Flask app MongoDB
  - pulls data from JPL via API request?
- Discord web hook for alerting large object
- Data comes in custom format, transformed/enriched to match JPL?

Steps:

- NEO data model
- base SpringBoot API; CRUD on postgres; swagger
- Apache Kafka layer
- Shell write to Kafka
- base flask app; write to Mongodb
- Flask jinja frontend
- NiFi ingest MongoDB data
- NiFi ingest Kafka data
- NiFi transform custom NEO data to match JPL
- NiFi enrich NEO data?
- NiFi store to postgres
- NiFi ingest JPL data directly?
- Discord web hook alert for large dangerous object
- Very very simple interface over data at rest (Vue?)

TODO: diagram

## URLs

- Flask Asteroid API - http://localhost:9000
- SpringBoot Asteroid API - http://localhost:9001
- SpringBoot Asteroid API Swagger - http://localhost:9001/swagger-ui.html

## References

- NASA JPL NEO - https://cneos.jpl.nasa.gov/
