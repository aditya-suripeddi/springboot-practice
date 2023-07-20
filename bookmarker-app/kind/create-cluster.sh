#!/bin/bash

# NOTE: run the script after changing to parent directory of script:
#       $ cd /bookmarker-app/kind
#       as kind create cluster command below needs to read kind-config.yml

printf "Initializing k8s cluster with kind ...."

kind create cluster --config kind-config.yml

printf "\n---------------------------------------------\n"

printf "Installing NGINX Ingress..."

# https://superuser.com/questions/508507/linux-bash-script-single-command-but-multiple-lines
kubectl apply -f  \
      https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml



printf "\n---------------------------------------------\n"

printf "Waiting for NGINX Ingress to be ready"

sleep 10

kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=180s


printf "\nYour cluster is ready to use"