#!/usr/bin/env bash

cd "$(dirname "$0")" || exit 131

docker-compose down

bash remove.bash

docker-compose up -d
