# Etapa 1: Build da aplicação
FROM gradle:8.6-jdk21 AS builder

# Copie o projeto para o container
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

# Construa a distribuição (sem Shadow)
RUN gradle installDist --no-daemon

# Etapa 2: Imagem de runtime
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copie a pasta gerada pela installDist
COPY --from=builder /home/gradle/project/build/install /app

# Exponha a porta da API
EXPOSE 8080

# Rode o app com o script gerado (substitua 'nome-do-app' pelo rootProject.name do seu projeto)
ENTRYPOINT ["/app/api_trab_mobile/bin/api_trab_mobile"]
