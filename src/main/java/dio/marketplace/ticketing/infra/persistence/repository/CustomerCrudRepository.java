package dio.marketplace.ticketing.infra.persistence.repository;

import dio.marketplace.ticketing.infra.persistence.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface CustomerCrudRepository extends CrudRepository<Customer, UUID> {
}
