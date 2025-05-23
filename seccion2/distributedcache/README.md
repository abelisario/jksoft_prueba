# Proyecto: Cache Distribuido con Redis 

Implementar un sistema de caché distribuido utilizando un lenguaje de su elección.

Considere factores como:
  - Almacenar y recuperar datos de manera eficiente.
  - Manejar problemas de consistencia distribuida.
  - Proporcionar mecanismos para la invalidación y caducidad del almacenamiento en caché.

Este proyecto implementa una **capa de cache distribuida** con **Spring Boot** y **Redis**, desplegado en un **clúster Kubernetes**. Se integra con Docker para contenerización y soporta **replicación de Redis** para alta disponibilidad.  

## 📌 Requisitos
Antes de comenzar, asegúrate de tener instalado:
- **Docker** (con Kubernetes habilitado en Docker Desktop)
- **kubectl** para gestionar Kubernetes
- **Redis** (para pruebas locales)
- **Maven** (`mvn`) para construcción y pruebas

docker run -p 8080:8080 -e SPRING_REDIS_HOST=host.docker.internal distributedcache:latest
curl -X POST http://localhost:8080/cache/testKey -H "Content-Type: application/json" -d "\"testValue\""
curl -X GET http://localhost:8080/cache/testKey
---

## Compilación con Maven
Ejecuta los siguientes comandos para compilar la aplicación y generar el JAR:
```sh
mvn clean install
```

## Pruebas unitarias
Ejecuta el siguiente comando para realizar pruebas unitarias:
```sh
mvn test
```

## Pruebas de integración
Ejecuta el siguiente comando para realizar pruebas de integración:
```sh
export SPRING_REDIS_HOST=localhost
export SPRING_REDIS_PORT=6379
mvn verify
```

# Ejecutar local, con apuntamiento a instancia de REDIS en HOST:PORT
```sh
java -jar -DSPRING_REDIS_HOST={HOST} -DSPRING_REDIS_PORT={PORT} target/distributedcache-0.0.1-SNAPSHOT.jar
```

## Generar y publicar imagen Docker en el registro local
```sh
docker build -t distributedcache:latest .
docker run -d -p 5000:5000 --name registry registry:2
docker tag distributedcache:latest localhost:5000/distributedcache:latest
docker push localhost:5000/distributedcache:latest
```

## Ejecución de contenedor Docker con Redis en localhost:6379

```sh
docker run -p 8080:8080 distributedcache:latest
```

## Ejecución de contenedor Docker con Redis como servicio externo en el mismo host de Docker
```sh
docker run -p 8080:8080 -e SPRING_REDIS_HOST={HOST} -e SPRING_REDIS_PORT={PORT} distributedcache:latest
```

## Ejecución de cluster Kubernetes
Ejecute el siguiente comando para iniciar la configuración de cluster pre-establecida (contiene 1 para de Redis y 1 pod para distributed-cache-app)
Puede editar el archivo kubernetes-deployent.yaml para incrementar el número de réplicas, asi como para habilitar replicación en Redis (configurar maestro y esclavo)  
```sh
kubectl apply -f kubernetes-deployment.yaml
```

## Comandos para pruebas

## Registrar una entrada
```sh
curl -X POST http://localhost:8080/cache/testKey -H "Content-Type: application/json" -d "\"testValue\""}}
```

## Consultar entrada
```sh
curl -X GET http://localhost:8080/cache/testKey
```