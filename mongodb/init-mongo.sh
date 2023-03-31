#!/bin/sh

# init mongodb

set -e

mongosh <<EOF
use admin

db = new Mongo().getDB('$MONGO_DB');

db.createUser({
  user: '$MONGO_USER',
  pwd: '$MONGO_PASSWORD',
  roles: [
    {
      role: 'readWrite',
      db: '$MONGO_DB'
    }
  ],
});
db.createCollection('asteroid');

EOF