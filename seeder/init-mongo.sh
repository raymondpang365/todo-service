#!/bin/bash
set -e

# Switch to the "admin" database to create a user, if you want auth
mongosh --eval "
if (!db.getUser('appuser')) {
  db.createUser({
    user: 'appuser',
    pwd: 'apppassword',
    roles: [ { role: 'readWrite', db: 'tododb' } ]
  });
} else {
  print('User \"appuser\" already exists. Skipping creation.');
}
"

# Enable sharding on the "tododb" database
#mongosh --eval "sh.enableSharding('tododb')"
#
## Shard the collection by listId
#mongosh --eval "sh.shardCollection('tododb.todoItems', { listId: 1 })"

# Now seed data:
SEED_SCRIPT="/docker-entrypoint-initdb.d/seed.js"
mongosh $SEED_SCRIPT