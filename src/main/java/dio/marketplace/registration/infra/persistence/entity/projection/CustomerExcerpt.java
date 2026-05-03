package dio.marketplace.registration.infra.persistence.entity.projection;

import dio.marketplace.registration.infra.persistence.entity.CustomerEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "excerpt", types = { CustomerEntity.class })
public interface CustomerExcerpt {

    String getFirstName();

    String getLastName();

    @Value("#{target.address?.toString()}")
    String getAddress();

}
