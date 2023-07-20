#!/bin/bash

declare dependent_services=docker-dependent-services.yml
declare bookmarker_api_and_ui=docker-app.yml

function build_api() {
    cd bookmarker-api
    ./mvnw clean package -DskipTests
    cd ..
}

function start_dependent_services() {
  echo "Starting bookmarker-api dependent services (docker containers) ...."
  docker compose -f ${dependent_services} up -d
  docker compose -f ${dependent_services} logs -f #   -f, --follow   Follow log output

}

function start_infra() {
  start_dependent_services
}


function stop_dependent_services() {
  echo "Stopping bookmarker_api dependent services  (docker containers) ...."
  docker compose -f ${dependent_services} stop
  docker compose -f ${dependent_services} rm -f   #   -f, --force     Don't ask to confirm removal
}

function stop_infra() {
  stop_dependent_services
}

function start() {

  build_api

  echo "Starting  bookmarker_api_and_ui services (docker containers) ...."

  # --build required to pick up code changes and rebuild
  # container images replacing previous ones
  docker compose -f ${dependent_services} -f ${bookmarker_api_and_ui} up -d --build
  docker compose -f ${dependent_services} -f ${bookmarker_api_and_ui} logs -f  #   -f, --follow   Follow log output

}

function stop() {
  echo "Stopping all bookmarker_api services (docker containers) ..."
  docker compose  -f ${dependent_services} -f ${bookmarker_api_and_ui} stop
  docker compose  -f ${dependent_services} -f ${bookmarker_api_and_ui} rm -f
}

function restart() {
    stop
    sleep 3
    start
}

action="start"

if [[ "$#" != "0" ]]  # if arguments are passed, set action to arguments
then
  action=$@
fi

eval ${action}