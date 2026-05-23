## 📚 Estrutura de Testes - Guia de Navegação

Este documento guia você pela estrutura de testes implementada.

### 📁 Localização: `src/test/java/dio/marketplace/test/`

```
src/test/java/dio/marketplace/test/
├── base/                          # Classes abstratas base (reutilizáveis)
│   ├── AbstractIntegrationTest.java    # Para @SpringBootTest + Testcontainers
│   ├── AbstractDataJpaTest.java        # Para @DataJpaTest
│   ├── AbstractDataMongoTest.java      # Para @DataMongoTest
│   └── AbstractWebMvcTest.java         # Para @WebMvcTest
│
├── factory/                        # Geração de dados para testes
│   └── TestDataFactory.java       # Faker + builders aleatórios
│
└── examples/                       # Exemplos funcionais (copiar e adaptar!)
    ├── unit/
    │   └── SampleUnitTest.java               # Teste unitário (70% pirâmide)
    ├── integration/
    │   ├── SampleCustomerRepositoryIntegrationTest.java  # JPA + MySQL
    │   └── SampleRedisIntegrationTest.java               # Redis + Cache
    └── api/
        └── SampleCustomerControllerTest.java # Controller + MockMvc
```

---

## 🎯 Quando Usar Cada Tipo

| Tipo | Decorador | Containers | Tempo | Uso | Exemplo |
|------|-----------|-----------|-------|-----|---------|
| **Unit** | Nenhum | ❌ | ~5ms | Lógica pura, regras de negócio | Cálculos, validações |
| **JPA** | `@DataJpaTest` | ✅ MySQL | ~2s | Repository, queries | Find, Save, Delete |
| **Mongo** | `@DataMongoTest` | ✅ MongoDB | ~2s | Repository MongoDB | Document queries |
| **Redis** | `@SpringBootTest` | ✅ Redis | ~3s | Cache, pub/sub | Caching, sessions |
| **Controller** | `@WebMvcTest` | ❌ | ~500ms | REST endpoints | GET, POST, validation |
| **E2E** | `@SpringBootTest` | ✅ Todos | ~5s | Fluxos completos | Integração total |

---

## 🚀 Como Usar os Exemplos

### 1️⃣ Teste Unitário (Mais Rápido - 70% dos Testes)

Copie: `SampleUnitTest.java`

```bash
# Executar apenas testes unitários
./gradlew unitTest

# Ou via IDE
Right-click → Run with tag: @unit
```

**Quando usar:**
- Testar uma classe sem dependências
- Validações de negócio
- Transformações de dados
- Cálculos matemáticos

**Exemplo real:**
```java
@Test
@DisplayName("Deve validar email válido")
void shouldValidateEmail() {
    assertThat(isValidEmail("usuario@exemplo.com")).isTrue();
}
```

---

### 2️⃣ Teste JPA (Com Banco Real - 20% dos Testes)

Copie: `SampleCustomerRepositoryIntegrationTest.java`

```bash
# Executar apenas testes de integração
./gradlew integrationTest

# Ou específico
./gradlew test --tests "*RepositoryIntegrationTest"
```

**Estenda:**
```java
class YourRepositoryTest extends AbstractDataJpaTest {
    @Autowired
    private YourRepository repository;
    
    // Seu teste aqui
}
```

**Quando usar:**
- Testar queries customizadas
- Validar relacionamentos
- Testar filters e predicates
- Garantir persistência

---

### 3️⃣ Teste MongoDB (Documento Flexível)

Similar ao JPA, mas herda `AbstractDataMongoTest`:

```java
class YourMongoTest extends AbstractDataMongoTest {
    @Autowired
    private MongoTemplate mongoTemplate;
    // Seus testes...
}
```

---

### 4️⃣ Teste Redis (Cache e Sessão)

Copie: `SampleRedisIntegrationTest.java`

```java
class YourRedisTest extends AbstractIntegrationTest {
    @Autowired
    private RedisTemplate<String, Object> redis;
    // Seus testes...
}
```

**Operações comuns:**
```java
// String
redis.opsForValue().set("key", "value");
redis.opsForValue().get("key");

// Hash (tipo objeto)
redis.opsForHash().put("user:1", "name", "João");
redis.opsForHash().get("user:1", "name");

// List (fila)
redis.opsForList().rightPush("queue", "item");

// Set (conjunto)
redis.opsForSet().add("tags", "java", "spring");

// Com TTL
redis.opsForValue().set("key", "value", 1, TimeUnit.HOURS);
```

---

### 5️⃣ Teste Controller (API REST)

Copie: `SampleCustomerControllerTest.java`

```java
@WebMvcTest(YourController.class)
class YourControllerTest extends AbstractWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private YourService service;
    
    @Test
    void shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/resource"))
            .andExpect(status().isOk());
    }
}
```

**Verificações comuns:**
```java
// Status HTTP
.andExpect(status().isOk())
.andExpect(status().isCreated())
.andExpect(status().isNotFound())

// Response body (JSON)
.andExpect(jsonPath("$.id", is(1)))
.andExpect(jsonPath("$.name", is("João")))

// Headers
.andExpect(header().exists("Content-Type"))
.andExpect(header().string("X-Custom", "value"))

// Content type
.andExpect(content().contentType(MediaType.APPLICATION_JSON))
```

---

### 6️⃣ Teste E2E Completo (Integração Total)

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class YourE2ETest extends AbstractIntegrationTest {
    @Autowired
    private TestRestTemplate rest;
    
    @Autowired
    private YourRepository repository;
    
    @Test
    void shouldCompleteFlow() {
        // Salva no banco
        var entity = repository.save(new Entity());
        
        // Chama API
        var response = rest.getForEntity("/api/entities/{id}", EntityDto.class, entity.getId());
        
        // Valida
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
```

---

## 🏗️ Arquitetura de Containers

### Estratégia de Reutilização

```
┌─────────────────────────────────────────────────┐
│         AbstractIntegrationTest                 │
│  (Containers COMPARTILHADOS - Singleton)        │
├─────────────────────────────────────────────────┤
│ - MYSQL_REGISTRATION ──┐                       │
│ - MYSQL_CATALOG        ├─ @DynamicPropertySource
│ - MONGODB              │   Usado em: @SpringBootTest
│ - REDIS                │   Compartilhados entre testes
└─────────────────────────────────────────────────┘
         ↑ Herdar para testes E2E

┌─────────────────────────────────────────────────┐
│         AbstractDataJpaTest                     │
│  (Container ISOLADO - Por classe)               │
├─────────────────────────────────────────────────┤
│ - MySQL (dedicado)     └─ @DynamicPropertySource
│   Usado em: @DataJpaTest
│   Isolado por classe de teste
└─────────────────────────────────────────────────┘
```

**Por que assim?**
- ✅ Compartilhados em E2E: melhor performance
- ✅ Isolados em unit/component: máxima segurança
- ✅ Sem poluição de dados entre testes

---

## 📊 Test Pyramid (Distribuição Recomendada)

```
        ▲
       ╱ ╲         10% - E2E/API Tests
      ╱   ╲        (Testes com @SpringBootTest completo)
     ╱─────╲
    ╱       ╲      20% - Integration Tests
   ╱─────────╲     (Repository, Cache, Events)
  ╱           ╲
 ╱─────────────╲   70% - Unit Tests
╱───────────────╲  (Lógica pura, sem containers)
```

**Implementar assim:**
- 70% `SampleUnitTest` style → `./gradlew unitTest`
- 20% `SampleCustomerRepositoryIntegrationTest` style → `./gradlew integrationTest`
- 10% `SampleCustomerControllerTest` + full E2E

---

## 🧪 TestDataFactory (Dados Aleatórios)

Gere dados realistas para testes:

```java
import static dio.marketplace.test.factory.TestDataFactory.*;

// Strings
randomEmail()          // usuario@dominio.com
randomName()           // João da Silva
randomPhone()          // (11) 98765-4321
randomAddress()        // Rua X, 123, São Paulo, SP

// Números
randomNumber(100)      // 0-99
randomNumber(10, 20)   // 10-19
randomPrice()          // 10.00 - 10000.00

// Dados de negócio
randomCategory()       // "Eletrônicos", "Moda", etc
randomSku()           // SKU-XXXXXXXXXXXXX
randomUrl()           // https://example.com
randomDescription()   // Sentenças aleatórias

// Booleano
randomBoolean()       // true ou false
```

**Uso em testes:**
```java
@Test
void shouldSaveWithRandomData() {
    var customer = new Customer();
    customer.setEmail(randomEmail());
    customer.setName(randomName());
    
    repository.save(customer);
    // Teste com dados variados!
}
```

---

## ✅ Checklist de Boas Práticas

- ✅ Use `@BeforeEach` para limpeza (não `@Transactional`)
- ✅ Nomeie testes com `should...When...` ou `test...`
- ✅ Use `@DisplayName` com descrição legível
- ✅ Organize com `@Nested` para cenários relacionados
- ✅ Use `@Tag("unit")` e `@Tag("integration")` para separação
- ✅ Use `TestDataFactory` para dados aleatórios
- ✅ Use `AssertJ` para assertions fluentes
- ✅ Evite `@Transactional` (mascara bugs)
- ✅ Evite `Thread.sleep()` (use Testcontainers)
- ✅ Evite hardcoding de IDs (use TestDataFactory)
- ✅ Evite testes que dependem de execução de outros
- ✅ Use `@MockBean` apenas quando necessário

---

## 🚀 Executando Testes

```bash
# Todos os testes
./gradlew test

# Apenas unitários
./gradlew unitTest

# Apenas integração
./gradlew integrationTest

# Específico
./gradlew test --tests "SampleUnitTest"

# Com cobertura
./gradlew test jacocoTestReport

# Report em: build/reports/jacoco/test/html/index.html
```

---

## 🎓 Próximas Etapas

1. Leia os exemplos fornecidos
2. Copie um exemplo similar ao que você precisa
3. Adapte classe, métodos e dados
4. Execute e valide
5. Repita!

Boa sorte! 🚀

