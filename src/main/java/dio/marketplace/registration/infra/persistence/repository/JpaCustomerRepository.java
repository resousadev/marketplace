package dio.marketplace.registration.infra.persistence.repository;

import dio.marketplace.registration.domain.Customer;
import dio.marketplace.registration.domain.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

    private final CustomerEntityRepository customerEntityRepository;

    public JpaCustomerRepository(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return List.of();
    }
}
