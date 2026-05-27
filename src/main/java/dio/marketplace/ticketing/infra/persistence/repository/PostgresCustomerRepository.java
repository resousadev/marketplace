package dio.marketplace.ticketing.infra.persistence.repository;

import dio.marketplace.ticketing.domain.CustomerRepository;
import dio.marketplace.ticketing.domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresCustomerRepository implements CustomerRepository {

  private final CustomerCrudRepository customerCrudRepository;

  public PostgresCustomerRepository(CustomerCrudRepository customerCrudRepository) {
    this.customerCrudRepository = customerCrudRepository;
  }

  @Override
  public void save(Customer customer) {
    var entity = new dio.marketplace.ticketing.infra.persistence.entity.Customer(
      customer.getId(),
      customer.getCorrelationId().id(),
      customer.getName()
    );
    customerCrudRepository.save(entity);
  }
}
