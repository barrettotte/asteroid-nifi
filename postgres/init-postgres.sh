#!/bin/bash

# init postgres with asteroid user, databases, and table

set -e

asteroid_db=asteroid

psql -v ON_ERROR_STOP=1 --username postgres <<-EOF
    CREATE DATABASE $asteroid_db;
    CREATE USER $POSTGRES_ASTEROID_USER WITH PASSWORD '$POSTGRES_ASTEROID_PASSWORD';
    GRANT ALL PRIVILEGES ON DATABASE $asteroid_db TO $POSTGRES_ASTEROID_USER;
EOF

psql -v ON_ERROR_STOP=1 --username postgres --dbname $asteroid_db <<-EOF
    CREATE TABLE IF NOT EXISTS $asteroid_db (
        id VARCHAR PRIMARY KEY,
        name VARCHAR NOT NULL,
        diameter_min NUMERIC NOT NULL,
        diameter_max NUMERIC NOT NULL,
        hazard BOOLEAN DEFAULT FALSE,
        relative_velocity NUMERIC NOT NULL,
        distance NUMERIC NOT NULL,
        orbiting_body VARCHAR NOT NULL,
        created TIMESTAMP NOT NULL,
        created_by VARCHAR NOT NULL,
        updated TIMESTAMP NOT NULL,
        updated_by VARCHAR NOT NULL
    );

    CREATE INDEX IF NOT EXISTS idx_asteroid_orbiting_body ON asteroid(orbiting_body);
    CREATE INDEX IF NOT EXISTS idx_asteroid_created ON asteroid(created);

    GRANT ALL PRIVILEGES ON TABLE $asteroid_db TO $POSTGRES_ASTEROID_USER;
EOF
