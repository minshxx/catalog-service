## Start kubernetes cluster
    minikube start --cpus 2 --memory 4g --driver docker --profile polar

    minikube image load catalog-service --profile polar

    kubectl apply -f k8s/deployment.yml

    kubectl apply -f k8s/service.yml

    kubectl port-forward service/catalog-service 9001:80