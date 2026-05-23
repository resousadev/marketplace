package dio.marketplace.test.factory;

import net.datafaker.Faker;
import java.util.Locale;
import java.util.UUID;

/**
 * TestDataFactory - Gerador centralizado de dados para testes.
 *
 * Fornece métodos fluentes para criar objetos de teste com dados
 * realistas usando Faker, evitando duplicação de código entre testes.
 *
 * Benefícios:
 * - Dados consistentes e realistas
 * - Facilita manutenção (mudanças em um lugar)
 * - Reduz acoplamento entre testes
 * - Código mais limpo e legível
 *
 * @author Staff Engineer - Java 25 + Spring Boot 4.0.6
 * @since 1.0.0
 */
public final class TestDataFactory {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    private TestDataFactory() {
        // Utility class - não instanciar
    }

    // ========== UUID ==========

    /**
     * Gera um UUID aleatório.
     * @return UUID único
     */
    public static UUID randomUuid() {
        return UUID.randomUUID();
    }

    // ========== Strings ==========

    /**
     * Gera um email aleatório válido.
     * @return Email no formato usuario@dominio.com
     */
    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Gera um nome aleatório em português.
     * @return Nome completo
     */
    public static String randomName() {
        return faker.name().fullName();
    }

    /**
     * Gera um telefone aleatório válido (formato brasileiro).
     * @return Telefone no formato (XX) XXXXX-XXXX
     */
    public static String randomPhone() {
        return faker.phoneNumber().cellPhone();
    }

    /**
     * Gera um endereço aleatório.
     * @return Endereço completo
     */
    public static String randomAddress() {
        return faker.address().fullAddress();
    }

    /**
     * Gera uma string aleatória de comprimento específico.
     * @param length Comprimento desejado
     * @return String alfanumérica
     */
    public static String randomString(int length) {
        return faker.lorem().characters(length, true);
    }

    /**
     * Gera um número aleatório entre 0 e max.
     * @param max Valor máximo (exclusivo)
     * @return Número aleatório
     */
    public static int randomNumber(int max) {
        return faker.number().numberBetween(0, max);
    }

    /**
     * Gera um número aleatório entre min e max.
     * @param min Valor mínimo (inclusivo)
     * @param max Valor máximo (exclusivo)
     * @return Número aleatório
     */
    public static int randomNumber(int min, int max) {
        return faker.number().numberBetween(min, max);
    }

    /**
     * Gera um valor boolean aleatório.
     * @return true ou false com 50% de chance cada
     */
    public static boolean randomBoolean() {
        return faker.random().nextBoolean();
    }

    // ========== Dados de Negócio ==========

    /**
     * Gera um nome de categoria de produto/evento aleatório.
     * @return Categoria
     */
    public static String randomCategory() {
        String[] categories = {
            "Eletrônicos", "Moda", "Casa", "Esportes",
            "Livros", "Alimentos", "Beleza", "Brinquedos"
        };
        return categories[randomNumber(categories.length)];
    }

    /**
     * Gera um preço aleatório entre 10 e 10000.
     * @return Preço em reais
     */
    public static double randomPrice() {
        return faker.number().randomDouble(2, 10, 10000);
    }

    /**
     * Gera um SKU (número de referência de produto) aleatório.
     * @return SKU no formato SKU-XXXXXXXXXXXXX
     */
    public static String randomSku() {
        return "SKU-" + faker.code().isbnRegistrant();
    }

    /**
     * Gera um URL aleatório válido.
     * @return URL com protocolo https
     */
    public static String randomUrl() {
        return "https://" + faker.internet().domainName();
    }

    /**
     * Gera descrição aleatória (3-5 sentenças).
     * @return Descrição
     */
    public static String randomDescription() {
        return faker.lorem().sentence(randomNumber(3, 6));
    }

    /**
     * Gera um título/nome aleatório para eventos/produtos.
     * @return Título
     */
    public static String randomTitle() {
        return faker.lorem().sentence(randomNumber(3, 6));
    }

    // ========== Builders para Dados Complexos ==========

    /**
     * Retorna uma nova instância de Faker para uso customizado.
     * @return Faker configurado com locale pt-BR
     */
    public static Faker getFaker() {
        return faker;
    }
}

