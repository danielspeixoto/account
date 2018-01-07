#!/usr/bin/env bash

echo "Creating regular user credentials"
mongo admin --eval "db.createUser({
    user: '$ADMIN_USER',
    pwd: '$ADMIN_PASS',
    roles: [{
        role: 'regular',
        db: '$DB'
    }]
});"