#!/bin/bash

# Send a new asteroid to Kafka topic

set -e

kafka='localhost:9092'
bootstrap="--bootstrap-server $kafka"
brokers="--broker-list $kafka"
replication='--replication-factor 1'
partitions='--partitions 1'
topic='--topic asteroid'

asteroid_id=$(uuidgen -r)
asteroid_diameter=$(($RANDOM % 100 + 1))
asteroid_velocity=$(($RANDOM % 100 + 1))
asteroid_distance=$(($RANDOM % 1000 + 1))
hazard=$(($RANDOM % 2))

if [[ $hazard == 1 ]]; then
  hazard='true'
else
  hazard='false'
fi

asteroid_json_raw=$(cat <<-EOF
  {
    "id": "$asteroid_id",
    "name": "Asteroid $(echo $asteroid_id | cut -c1-8)",
    "diameter_min": $(($asteroid_diameter )),
    "diameter_max": $(($asteroid_diameter + 1)),
    "hazard": $hazard,
    "relative_velocity": $asteroid_velocity,
    "distance": $asteroid_distance,
    "orbiting_body": "earth"
  }
EOF
)
asteroid_json=$(echo $asteroid_json_raw | jq -c .)
echo $asteroid_json

# send asteroid to kafka, creating topic if not exists
(docker exec -i kafka sh <<-EOF
    kafka-topics.sh --create $bootstrap $replication $partition $topic --if-not-exists
    echo "$asteroid_json" | kafka-console-producer.sh $brokers $topic
EOF
) || (echo 'failed to send asteroid.'; exit 1)

# output number of messages in topic
msg_count=$(docker exec kafka sh -c "./opt/bitnami/kafka/bin/kafka-run-class.sh kafka.tools.GetOffsetShell $brokers $topic")
echo $msg_count
