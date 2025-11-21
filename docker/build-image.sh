#!/bin/bash
tag="v6"

echo "Working directory: $(pwd)"

echo "## Package JAR"
./mvnw clean package

echo "## RMI $tag"
docker rmi bproenca/oci-app-bcp:$tag

echo "## Build docker image"
docker build -f ./docker/Dockerfile -t bproenca/oci-app-bcp:$tag .
