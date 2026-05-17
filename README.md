# Marketplace

Projeto de exemplo em Java com Spring Boot que implementa funcionalidades básicas de catálogo (events) e registro de clientes. O objetivo deste README é fornecer um guia rápido para executar o projeto localmente, entender a arquitetura e testar os endpoints principais.

## Tecnologias
- Java (toolchain configurado no `build.gradle`)
- Spring Boot
- Spring Data JPA
- Spring Data REST
- Spring Data MongoDB
- Spring Data Redis
- Gradle (wrapper disponível)

Observação: o arquivo `build.gradle` define um toolchain Java via `JavaLanguageVersion.of(25)`. Ajuste o toolchain ou instale a versão de JDK adequada conforme sua necessidade (o projeto funciona com JDKs modernos; se não tiver JDK 25, atualize o `build.gradle` para a versão instalada).

## Visão geral da arquitetura
- Módulo `registration`: configura DataSource primário (MySQL) e repositórios JPA para entidade `CustomerEntity`.
- Módulo `catalog`: configura DataSource separado para catálogo (MySQL), integra MongoDB (metadados de evento) e Redis (cache) e expõe endpoints para `Event`/`Showcase`.
- Exposição REST: Controllers manuais (ex.: `ShowcaseController`) e repositórios Spring Data REST (ex.: `CustomerEntityRepository`, `EventEntityRepository`) que publicam endpoints automaticamente.

## Estrutura do repositório
```
marketplace/
├── build.gradle
├── settings.gradle
├── src/
│   ├── main/
│   │   ├── java/dio/marketplace/...
│   │   └── resources/application.yaml
│   └── test/
└── README.md
```

## Pré-requisitos
- JDK instalado (ver nota acima sobre toolchain). Recomendado JDK 17+.
- Internet para baixar dependências (Maven Central) ou desligue recursos que dependam de redes externas.

Use o Gradle Wrapper incluído no projeto para garantir a versão correta do Gradle.

## Como executar

1) Build do projeto:

```bash
./gradlew build
```

2) Executar a aplicação (modo desenvolvimento):

```bash
./gradlew bootRun
```

3) Executar o JAR gerado (após build):

```bash
java -jar build/libs/marketplace-0.0.1-SNAPSHOT.jar
```

A aplicação, por padrão, inicia em `http://localhost:8080`.

## Propriedades importantes (configuração)
As propriedades principais estão em `src/main/resources/application.yaml` (habilitam múltiplos datasources e caches):

- `registration.datasource`: MySQL para módulo de registro (ex.: jdbc:mysql://localhost:3307/registration)
- `catalog.datasource`: MySQL para catálogo (ex.: jdbc:mysql://localhost:3308/catalog)
- MongoDB (configurado em `CatalogConfiguration`) apontando para `localhost:27018` no projeto
- Redis (cliente `jedis`) para caches do catálogo

Se executar localmente, verifique se os bancos (MySQL/Mongo/Redis) estão disponíveis nas portas configuradas ou adapte o `application.yaml` para sua infraestrutura.

## Endpoints principais e exemplos com curl

1) Showcase (catálogo de eventos)

- GET /showcase — lista de eventos enriquecidos

Exemplo:

```bash
curl --location --request GET 'http://localhost:8080/showcase'
```

2) Clientes (expostos via Spring Data REST)

- GET /customers — lista de clientes (padrão Spring Data REST)
- POST /customers — criar cliente
- GET /customers/{id} — obter cliente por id
- Busca customizada: `/customers/search/findByFirstNameStartsWithIgnoreCase?firstName=Jo` (método definido em `CustomerEntityRepository`)

Exemplo — listar clientes:

```bash
curl --location --request GET 'http://localhost:8080/customers'
```

Exemplo — criar cliente (POST):

```bash
curl --location --request POST 'http://localhost:8080/customers' \
  --header 'Content-Type: application/json' \
  --data-raw '{
    "firstName": "João",
    "lastName": "Silva",
    "email": "joao.silva@example.com",
    "address": {
      "street": "Rua Exemplo, 123",
      "postalCode": "01234-567",
      "city": "Cidade",
      "state": "SP"
    }
  }'
```

Observação: a entidade `CustomerEntity` contém validações (`@NotBlank` em `firstName` e `email`, `@Email` em `email`) e cria `id` automaticamente no `@PrePersist`.

3) Eventos (expostos via `EventEntityRepository`):

- GET /events — lista de eventos persistidos (Spring Data REST)

```bash
curl --location --request GET 'http://localhost:8080/events'
```

## Testes

Executar testes:

```bash
./gradlew test
```

## Troubleshooting (itens comuns)

- Porta 8080 em uso — ajuste `server.port` em `application.yaml` ou libere a porta.
- Banco MySQL não disponível nas portas (3307/3308) — atualize `application.yaml` ou execute containers/instâncias locais.
- MongoDB/Redis: verifique se os serviços estão ativos nas portas configuradas. Em `CatalogConfiguration` o Mongo é configurado para `localhost:27018`.
- Se o build falhar por versão Java, ajuste o toolchain em `build.gradle` (propriedade `languageVersion`) para a versão instalada ou instale a JDK correspondente.

## Contribuição

Pull requests são bem-vindos. Fluxo sugerido:
1. Fork o repositório
2. Crie uma branch para sua feature/fix
3. Faça commit das suas alterações
4. Abra um Pull Request

## Licença
Este projeto está sob a licença MIT.

