package dio.marketplace.catalog.infra.persistence.repository;

import dio.marketplace.catalog.infra.persistence.entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventEntityRepository extends CrudRepository<Event, UUID> {
}
