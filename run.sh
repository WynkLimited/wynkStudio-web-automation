#!/bin/bash
./env_file.sh > env_file.txt
ROOT=`pwd`
docker run --env-file env_file.txt --rm \
    -v ${ROOT}:/root/src \
    prateekladha/java-maven-chrome:latest \
    ./command_for_docker.sh
