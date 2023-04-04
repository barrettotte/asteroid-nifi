#!/bin/bash

# Push multiple asteroids to kafka topic

set -e

asteroids=10

for (( i = 0; i < $asteroids; i++ ))
do
  echo "Pushing asteroid ($(($i+1)) of $asteroids)"
  ./push-asteroid.sh
done

echo done.
