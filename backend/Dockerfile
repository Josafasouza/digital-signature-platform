# Usa uma imagem base com a versao LTS do Java 17
FROM openjdk:17-jdk-slim

# Define o diretorio de trabalho dentro do container
WORKDIR /app

# Copia o arquivo .jar da sua aplicacao para o container
# Este arquivo precisa ser gerado antes, com 'mvn package'
COPY target/digital-0.0.1-SNAPSHOT.jar /app/app.jar

# Expoe a porta que sua aplicacao Spring Boot usa
EXPOSE 8080

# Comando para rodar a aplicacao quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]
