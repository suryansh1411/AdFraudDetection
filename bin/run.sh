cd ..
mvn clean install -DskipTests
docker rmi suryanshsingh/ad_fraud_detection:v3 -f
docker build --tag=suryanshsingh/ad_fraud_detection:v3 ./bin/
#
cd bin
#docker login --username suryanshsingh --password Devansh_123
#docker push suryanshsingh/ad_fraud_detection:v3
#
#cd ..
#minikube restart
#kubectl apply -f kube
#kubectl rollout restart deployment
#kubectl get pods

docker-compose up --build