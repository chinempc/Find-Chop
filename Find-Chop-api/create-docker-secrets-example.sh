#!/bin/zsh

# Declare an associative array of secrets
declare -A secrets
secrets=(
  ["rabbit-user"]=""
  ["rabbit-password"]=""
  ["rabbit-routing-key"]=""
  ["mongo-uri"]=""
  ["mongo-db-recipe"]=""
  ["mongo-db-click"]=""
  ["mongo-db-ingredient"]=""
)


# Create a temporary directory for storing secret files
mkdir -p /tmp/docker-secrets

# Loop through the secrets using zsh syntax
for secret_name in ${(k)secrets}; do
  secret_value=$secrets[$secret_name]

  echo "Creating secret: $secret_name with value: $secret_value"

  # Create a temporary file with the secret value
  echo "$secret_value" > /tmp/docker-secrets/"$secret_name"

  # Create the Docker secret
  docker secret create "$secret_name" /tmp/docker-secrets/"$secret_name"
done

# Clean up the temporary directory
rm -rf /tmp/docker-secrets
echo "Docker secrets created successfully!"

# Run The Following After
# chmod +x create-docker-secrets.sh
# ./create-docker-secrets.sh

