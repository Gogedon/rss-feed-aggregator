#!/bin/bash

# vars
TIMEOUT=${TIMEOUT:-60}
INTEVAL=${INTERVAL:-5}
END_TIME=$((SECONDS + TIMEOUT))

echo "Witing for PostgreSQL to be ready..."

while [ $SECONDS -lt $END_TIME ]; do
	if [ "`docker inspect -f {{.State.Health.Status}} $(docker-compose ps -q postgres)`" == "healthy" ] >/dev/null 2>&1; then
		echo "PostgreSQL is ready"
		exit 0
	fi
	echo "PostgreSQL is not ready yet. Waiting..."
	sleep $INTERVAL
done

echo "Timed out waiting for PostgreSQL to be ready."

exit 1
