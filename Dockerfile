# Etapa de build
FROM gradle:8.7-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle installDist

# Etapa de runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/install/ <api_trab_mobile> /app/
CMD ["/app/bin/<api_trab_mobile>"]

