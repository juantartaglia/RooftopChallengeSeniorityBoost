# Challenge técnico: Seniority Boost 2022 

Desarrollo de Challenge Técnico Seniority Boost 2022


## Construido con 🛠️

* [Java](https://adoptopenjdk.net/) - Java Version 11
* [SpringBoot](https://spring.io/) - Framework utilizado
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [H2](https://www.h2database.com/html/main.html) - Base de datos.


## Despliegue 📦

_El proyecto incluye archivo Dockerfile, el cual puede ser utilizado para su despliegue._


Generar de imagen.
```
docker build -t rooftop/challenge:2022 .
```
Ejecutar contenedor.
```
docker run --name challengeApp -p 8080:8080 rooftop/challenge:2022
```
