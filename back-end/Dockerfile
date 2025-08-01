# Estágio 1: Build da aplicação com Maven e Java 21
FROM maven:3.9-eclipse-temurin-21 AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o descritor do projeto (pom.xml)
COPY pom.xml .

# Copia o wrapper do Maven
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

# Baixa as dependências do projeto
RUN mvn dependency:go-offline

# Copia todo o código-fonte da aplicação
COPY src/ src/

# Compila a aplicação e gera o arquivo JAR
RUN mvn clean install -DskipTests

# Estágio 2: Imagem final, otimizada para execução
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho
WORKDIR /app

# Cria um usuário e grupo específicos para a aplicação (boa prática de segurança)
RUN addgroup -S spring && adduser -S spring -G spring

# Copia o JAR compilado do estágio de build
COPY --from=builder /app/target/*.jar app.jar

# Define o proprietário do arquivo da aplicação
RUN chown spring:spring app.jar

# Muda para o usuário não-root
USER spring

# Expõe a porta que a aplicação usa
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]