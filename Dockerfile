# Use uma imagem base apropriada, por exemplo, para uma aplicação Java
FROM openjdk:11-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR da aplicação para o contêiner
COPY target/app.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
