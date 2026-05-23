package dio.marketplace.registration.infra.persistence.repository;

import dio.marketplace.common.infra.event.dto.CustomerCreated;
import dio.marketplace.registration.domain.Customer;
import dio.marketplace.registration.domain.CustomerId;
import dio.marketplace.registration.domain.CustomerRepository;
import dio.marketplace.registration.infra.persistence.entity.CustomerEntity;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

    private final CustomerEntityRepository customerEntityRepository;
    private final ApplicationEventPublisher eventPublisher;

    public JpaCustomerRepository(CustomerEntityRepository customerEntityRepository, ApplicationEventPublisher eventPublisher) {
        this.customerEntityRepository = customerEntityRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Customer save(Customer customer) {
        var entity = toEntity(customer);
        var savedEntity = customerEntityRepository.save(entity);

        eventPublisher.publishEvent(new CustomerCreated(customer.getId().id().toString(), customer.getName()));

        return toDomain(savedEntity);
    }

    @Override
    public List<Customer> findAll() {
        var iterable = customerEntityRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(JpaCustomerRepository::toDomain)
                .toList();
    }

    private static CustomerEntity toEntity(Customer customerDomain) {
        var entity = new CustomerEntity();
        entity.setId(customerDomain.getId().id());
        entity.setFirstName(customerDomain.getName());
        entity.setEmail(customerDomain.getEmail());
        return entity;
    }

    private static Customer toDomain(CustomerEntity customerEntity) {
        String fullName = Optional.ofNullable(customerEntity.getLastName())
                .map(lastName -> customerEntity.getFirstName() + " " + lastName)
                .orElse(customerEntity.getFirstName());

        return new Customer(
                new CustomerId(customerEntity.getId()),
                fullName,
                customerEntity.getEmail()
        );
    }
}
