cd ..
mvn clean install -DskipTests
docker build --tag=ad_fraud_detection:v1 ./bin/
cd bin
docker-compose up --build