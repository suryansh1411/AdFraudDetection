cd ..
mvn clean install -DskipTests
docker build --tag=ad_fraud_detection:v1 ./bin/
cd bin

docker tag bb6c5dc3fdba suryanshsingh/ad_fraud_detection:v1
docker login --username suryanshsingh --password Devansh_123
docker push suryanshsingh/ad_fraud_detection:v1

docker-compose up --build
