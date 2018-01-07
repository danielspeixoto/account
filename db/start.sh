#!/bin/bash

# Starts mongo assuring that auth must be provided
# ip indicates that no remote access is allowed
mongod --auth --bind_ip 127.0.0.1 -storageEngine wiredTiger &

RET=1
while [[ RET -ne 0 ]]; do
    echo "=> Waiting for confirmation of MongoDB service startup"
    sleep 5
    mongo admin --eval "help" >/dev/null 2>&1
    RET=$?
done

./create-root-credentials.sh
./create-regular-user-credentials.sh

echo "=> Configuration Done! Have fun!"