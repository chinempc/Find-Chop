#!/bin/bash

while ! nc -z config-service 8888; do
  echo "Waiting for Config Service..."
  sleep 5
done

while ! nc -z discovery-server 8761; do
  echo "Waiting for Discovery Server..."
  sleep 5
done

echo "All dependencies are ready. Starting the application..."
exec java -jar /app/recipe-api.jar