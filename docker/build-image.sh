#!/bin/bash
tag="v4"

echo "Working directory: $(pwd)"

echo "## Package JAR"
./mvnw clean package

echo "## RMI $tag"
docker rmi bproenca/db-keep-alive-bcp-app:$tag

echo "## Build docker image"
docker build -f ./docker/Dockerfile -t bproenca/db-keep-alive-bcp-app:$tag .
