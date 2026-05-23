package dio.marketplace.test.examples.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

/**
 * Exemplo de Teste Unitário - Sem Containers, Sem @SpringBootTest
 *
 * Este é o tipo de teste MAIS RÁPIDO e deve ser 70% da sua suíte de testes.
 *
 * Características:
 * - ✅ Nenhuma dependência externa (MySQL, MongoDB, Redis)
 * - ✅ Execução em milissegundos (~5ms por teste)
 * - ✅ Ideal para: validações, conversões, cálculos, regras de negócio
 * - ✅ Sem mock de banco de dados necessário
 *
 * Padrão de Nomenclatura:
 * - Classe: SuaClasseTest (sufixo "Test")
 * - Método: testOuShould + O_que_testa + Quando + Resultado_esperado
 *
 * @author Staff Engineer - Java 25 + Spring Boot 4.0.6
 * @since 1.0.0
 *
 * @tag unit - Usado para filtrar apenas testes unitários (./gradlew unitTest)
 */
@Tag("unit")
@DisplayName("Exemplo de Teste Unitário Simples")
class SampleUnitTest {

    /**
     * ✅ BOM: Teste simples e rápido
     *
     * Testa uma classe de domínio ou utilitária sem dependências externas.
     * Execução: ~1ms
     */
    @Test
    @DisplayName("Deve validar email com formato correto")
    void shouldValidateCorrectEmail() {
        // DADO: Um email válido
        String email = "usuario@exemplo.com";

        // QUANDO: Validamos o email
        boolean isValid = isValidEmail(email);

        // ENTÃO: Deve ser válido
        assertThat(isValid).isTrue();
    }

    /**
     * ✅ BOM: Teste com diferentes dados (parametrizado)
     *
     * Evita código duplicado testando múltiplos cenários com uma implementação.
     * Execução: ~3ms no total
     */
    @ParameterizedTest(name = "Email: {0}")
    @ValueSource(strings = {
        "usuario@exemplo.com",
        "admin@dominio.com.br",
        "contato123@empresa.org"
    })
    @DisplayName("Deve validar emails com formatos distintos")
    void shouldValidateMultipleValidEmails(String email) {
        assertThat(isValidEmail(email)).isTrue();
    }

    /**
     * ✅ BOM: Testes organizados com @Nested
     *
     * Agrupa testes relacionados, melhorando legibilidade.
     * Especialmente útil para testar múltiplos cenários de uma feature.
     */
    @Nested
    @DisplayName("Validação de Senha")
    class PasswordValidationTests {

        @Test
        @DisplayName("Deve rejeitar senha muito curta")
        void shouldRejectShortPassword() {
            assertThat(isValidPassword("123")).isFalse();
        }

        @Test
        @DisplayName("Deve aceitar senha com 8 caracteres")
        void shouldAcceptValidPassword() {
            assertThat(isValidPassword("Senha@123")).isTrue();
        }

        @Test
        @DisplayName("Deve rejeitar senha sem números")
        void shouldRejectPasswordWithoutNumbers() {
            assertThat(isValidPassword("SenhaABC")).isFalse();
        }
    }

    /**
     * ✅ BOM: Testes de assertivas encadeadas (fluent assertions com AssertJ)
     *
     * Código mais legível e com melhor mensagem de erro quando falha.
     */
    @Test
    @DisplayName("Deve calcular preço final com desconto")
    void shouldCalculateFinalPriceWithDiscount() {
        // DADO
        double basePrice = 100.0;
        double discountPercent = 10.0;

        // QUANDO
        double finalPrice = calculateFinalPrice(basePrice, discountPercent);

        // ENTÃO
        assertThat(finalPrice)
            .isGreaterThan(0)
            .isEqualTo(90.0)
            .isNotNegative();
    }

    /**
     * ✅ BOM: Teste de exceção (Java 16+)
     *
     * Valida que a classe lança exceção em casos de erro.
     */
    @Test
    @DisplayName("Deve lançar exceção com valor negativo")
    void shouldThrowExceptionForNegativeValue() {
        assertThatThrownBy(() -> calculateFinalPrice(-100, 10))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("preço não pode ser negativo");
    }

    // ========== Métodos Auxiliares (Simulando Lógica Real) ==========

    /**
     * Simula validação de email (em produção seria regex ou biblioteca)
     */
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    /**
     * Simula validação de senha
     */
    private boolean isValidPassword(String password) {
        return password != null
            && password.length() >= 8
            && password.matches(".*\\d.*")  // Contém número
            && password.matches(".*[A-Z].*");  // Contém maiúscula
    }

    /**
     * Simula cálculo de preço com desconto
     */
    private double calculateFinalPrice(double basePrice, double discountPercent) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }
        return basePrice * (1 - discountPercent / 100);
    }
}

