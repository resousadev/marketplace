package dio.marketplace.registration.infra.persistence.repository;

import dio.marketplace.registration.infra.persistence.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface CustomerEntityRepository extends PagingAndSortingRepository<CustomerEntity, UUID>, CrudRepository<CustomerEntity, UUID> {
    List<CustomerEntity> findByFirstNameStartsWithIgnoreCase(@Param("firstName") String firstName);

    @Override
    @RestResource(exported = false)
    void deleteById(UUID uuid);
}
