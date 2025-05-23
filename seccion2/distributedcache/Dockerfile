# 1️⃣ Usamos una imagen base ligera de OpenJDK
FROM openjdk:17-jdk-slim

# 2️⃣ Definimos el directorio de trabajo dentro del contenedor
WORKDIR /app

# 3️⃣ Copiamos el archivo JAR generado por Maven
COPY target/distributedcache-0.0.1-SNAPSHOT.jar /app/distributedcache.jar

# 4️⃣ Exponemos el puerto en el que la aplicación corre (Spring Boot)
EXPOSE 8080

# 5️⃣ Definimos el comando de ejecución al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "/app/distributedcache.jar"]