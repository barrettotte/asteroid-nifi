#!/bin/bash

# fetch database drivers if not exists

set -e

driver_path=/opt/nifi/nifi-current/db-drivers

mkdir -p $driver_path

if [ ! -f "$driver_path/postgresql-42.6.0.jar" ]; then
  curl -O --output-dir $driver_path 'https://jdbc.postgresql.org/download/postgresql-42.6.0.jar'
fi

# original entrypoint
../scripts/start.sh
