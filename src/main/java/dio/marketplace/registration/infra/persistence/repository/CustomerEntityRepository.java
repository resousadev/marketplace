package dio.marketplace.registration.infra.persistence.repository;

import dio.marketplace.registration.infra.persistence.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerEntityRepository extends CrudRepository<CustomerEntity, UUID> {
}
