# Marketplace

Este projeto é um sistema de marketplace desenvolvido em Java utilizando o framework Spring Boot. Ele oferece funcionalidades para cadastro, consulta e gerenciamento de clientes, com persistência de dados e endpoints RESTful.

## Tecnologias Utilizadas
- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Data REST
- H2 Database (ou outro banco relacional)
- Gradle

## Estrutura do Projeto
```
marketplace/
├── src/
│   ├── main/
│   │   ├── java/dio/marketplace/...
│   │   └── resources/application.yaml
│   └── test/
│       ├── java/dio/marketplace/...
│       └── resources/application.yaml
├── build.gradle
├── settings.gradle
└── ...
```

## Como Executar

1. **Pré-requisitos:**
   - Java 17 ou superior instalado
   - Gradle instalado (ou use o wrapper `./gradlew`)

2. **Build do projeto:**
   ```bash
   ./gradlew build
   ```

3. **Executar a aplicação:**
   ```bash
   ./gradlew bootRun
   ```
   Ou execute o JAR gerado em `build/libs/`:
   ```bash
   java -jar build/libs/marketplace-0.0.1-SNAPSHOT.jar
   ```

4. **Acessar a API:**
   - A aplicação estará disponível em: `http://localhost:8080`
   - Endpoints REST para entidades como clientes estarão disponíveis automaticamente via Spring Data REST.

## Testes
Para rodar os testes automatizados:
```bash
./gradlew test
```

## Endpoints REST
Os endpoints são expostos automaticamente pelo Spring Data REST. Exemplos:
- `GET /customers` — Lista clientes
- `POST /customers` — Cria um novo cliente
- `GET /customers/{id}` — Detalhes de um cliente

## Contribuição
Pull requests são bem-vindos. Para contribuir:
1. Fork o repositório
2. Crie uma branch para sua feature/fix
3. Faça commit das suas alterações
4. Abra um Pull Request

## Licença
Este projeto está sob a licença MIT.

