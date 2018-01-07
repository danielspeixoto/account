#!/bin/bash
set -m

mongod -f /mongod.yml &

RET=1
while [[ RET -ne 0 ]]; do
    echo "=> Waiting for confirmation of MongoDB service startup"
    sleep 5
    RET=$?
done

/scripts/user/create-root-credentials.sh
# Login as admin
mongo admin -u ${ADMIN_USER} -p ${ADMIN_PASS} &
/scripts/user/create-regular-user-credentials.sh
mongo admin -u ${USER} -p ${PASS} &

echo "=> Configuration Done! Have fun!"

sleep 5000
fg