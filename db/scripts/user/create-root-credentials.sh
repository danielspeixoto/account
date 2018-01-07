#!/usr/bin/env bash

echo "=> Creating root credentials"
mongo admin --eval "
    db.createUser({
        user: '$ADMIN_USER',
        pwd: '$ADMIN_PASS',
        roles: [{
            role: 'root',
            db: 'admin'
        }]
    });"