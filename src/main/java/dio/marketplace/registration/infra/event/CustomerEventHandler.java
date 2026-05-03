package dio.marketplace.registration.infra.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RepositoryEventHandler
public class CustomerEventHandler {

    @HandleAfterCreate
    public void handleCustomerCreated(Object entity) {
        log.warn("Customer created: {}", entity);
    }

    @HandleAfterSave
    public void handleCustomerUpdated(Object entity) {
        log.warn("Customer updated: {}", entity);
    }

    @HandleAfterDelete
    public void handleCustomerDeleted(Object entity) {
        log.warn("Customer deleted: {}", entity);
    }

}
