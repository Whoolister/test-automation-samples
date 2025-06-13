#!/bin/bash
# This script clones the Restful-Booker API Playground, if it isn't already present, and runs it.

# Check if we have Docker and Docker Compose installed
if ! command -v docker >/dev/null 2>&1; then
    echo "Docker is not installed."
    exit 1
fi;


# Check if the directory exists
if [ ! -d "restful-booker" ]; then
    echo "Cloning Restful-Booker API Playground repository..."
    git clone https://github.com/mwinteringham/restful-booker.git
fi;

# Verify if the repository was cloned successfully
if [ ! -d "restful-booker" ]; then
    echo "Failed to clone the repository. Please check your internet connection or the repository URL."
    exit 1
fi;

# Navigate to the cloned repository
cd restful-booker || exit 1

docker compose down
docker compose build
docker compose up -d

# Wait for the server to start, for 10 seconds, with this command: http://localhost:3001/ping
MAX_ATTEMPTS=20
SLEEP_SECONDS=0.5

echo "Waiting for server to start..."
for ((i=1; i<=MAX_ATTEMPTS; i++)); do
    RESULT=$(curl -s -k http://localhost:3001/ping)

    if [[ "$RESULT" == "Created" ]]; then
        echo "Server is up and running!"
        exit 0
    else
      echo "Attempt $i of $MAX_ATTEMPTS - Server not ready yet..."
      sleep $SLEEP_SECONDS
    fi;
done

echo "Server did not respond with HTTP 200 after $((MAX_ATTEMPTS * SLEEP_SECONDS)) seconds."
docker compose down
exit 1