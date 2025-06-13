#!/bin/bash
echo "Tearing down Restful-Booker API Playground..."
cd restful-booker || exit 1
docker compose down
