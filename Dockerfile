FROM openjdk
WORKDIR /usr/src/order-system
COPY . /usr/src/order-system/

CMD ["java","-jar","target/OrderSystem-0.0.1-SNAPSHOT.jar"]