#!/bin/bash

# vars
TIMEOUT=${TIMEOUT:-60}
INTEVAL=${INTERVAL:-5}
END_TIME=$((SECONDS + TIMEOUT))

echo "Waiting for Keycloak to be ready..."

while [ $SECONDS -lt $END_TIME ]; do
	if [ "`docker inspect -f {{.State.Health.Status}} $(docker-compose ps -q keycloak)`" == "healthy" ] >/dev/null 2>&1; then
		echo "Keycloak is ready"
		exit 0
	fi
	echo "Keycloak is not ready yet. Waiting..."
	sleep $INTERVAL
done

echo "Timed out waiting for Keycloak to be ready."

exit 1
