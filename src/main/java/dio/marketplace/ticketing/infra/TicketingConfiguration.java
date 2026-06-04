package dio.marketplace.ticketing.infra;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.jpa.autoconfigure.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.LinkedHashMap;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(
    basePackages = "dio.marketplace.ticketing",
    entityManagerFactoryRef = "ticketingEntityManagerFactory",
    transactionManagerRef = "ticketingTransactionManager"
)
public class TicketingConfiguration {

  @Qualifier("ticketingDataSourceProperties")
  @Bean(defaultCandidate = false)
  @ConfigurationProperties(prefix = "ticketing.datasource")
  public DataSourceProperties ticketingDataSourceProperties() {
    // Configuração das propriedades do DataSource para o módulo de ticketing
    return new DataSourceProperties();
  }

   @Qualifier("ticketingDataSource")
   @Bean(defaultCandidate = false)
   public HikariDataSource ticketingDataSource(@Qualifier("ticketingDataSourceProperties") DataSourceProperties properties) {
     // Configuração do DataSource para o módulo de ticketing
     return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
   }

   @Qualifier("ticketingJpaProperties")
   @Bean(defaultCandidate = false)
   @ConfigurationProperties("ticketing.jpa")
   public JpaProperties ticketingJpaProperties() {
     // Configuração das propriedades JPA para o módulo de ticketing
     return new JpaProperties();
   }

   @Bean(defaultCandidate = false)
   @Qualifier("ticketingEntityManagerFactory")
   public LocalContainerEntityManagerFactoryBean ticketingEntityManagerFactory(
       @Qualifier("ticketingDataSource") HikariDataSource dataSource,
       @Qualifier("ticketingJpaProperties") JpaProperties jpaProperties
   ) {
    // Configuração do EntityManagerFactory para o módulo de ticketing
    var builder = new EntityManagerFactoryBuilder(
        new HibernateJpaVendorAdapter(),
        x -> new LinkedHashMap<>(jpaProperties.getProperties()),
        null
    );

    return builder
        .dataSource(dataSource)
        .packages("dio.marketplace.ticketing")
        .persistenceUnit("ticketing")
        .build();
  }

   @Bean(defaultCandidate = false)
   @Qualifier("ticketingTransactionManager")
   public PlatformTransactionManager ticketingTransactionManager(
       @Qualifier("ticketingEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf
   ) {
    // Configuração do TransactionManager para o módulo de ticketing
    return new JpaTransactionManager(emf.getObject());
  }

}
