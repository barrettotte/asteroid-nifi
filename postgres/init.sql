CREATE TABLE IF NOT EXISTS asteroid (
    id VARCHAR PRIMARY KEY,
    name VARCHAR NOT NULL,
    diameter_min NUMERIC NOT NULL,
    diameter_max NUMERIC NOT NULL,
    hazard BOOLEAN NOT NULL,
    relative_velocity NUMERIC NOT NULL,
    distance NUMERIC NOT NULL,
    orbiting_body VARCHAR NOT NULL
);
