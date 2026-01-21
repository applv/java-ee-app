#!/bin/sh

docker rm -f wildfly

docker rmi

docker rmi oracle.linux.v9-wildfly:latest

docker compose up
