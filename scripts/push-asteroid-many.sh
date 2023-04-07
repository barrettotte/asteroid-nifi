#!/bin/bash

# Push multiple asteroids to kafka topic

set -e

asteroids=10
script_dir=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

for (( i = 0; i < $asteroids; i++ ))
do
  echo "Pushing asteroid ($(($i+1)) of $asteroids)"
  $script_dir/push-asteroid.sh
done

echo done.
