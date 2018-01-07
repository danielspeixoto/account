#!/usr/bin/env bash

echo "=> Creating regular user credentials"

# Creating role
ROLE=regular

mongo admin --eval "
    db.createRole({
        role: '$ROLE',
        privileges: [
            {
                resource: {
                    db: '$DB',
                    collection: ''
                },
                actions: [
                    'find',
                    'insert',
                    'createIndex'
                ]
            }
       ],
       roles: [
       ]
    });
    "

mongo admin --eval "
    db.createUser({
        user: '$USER',
        pwd: '$PASS',
        roles: [
            '$ROLE'
        ]
    });"