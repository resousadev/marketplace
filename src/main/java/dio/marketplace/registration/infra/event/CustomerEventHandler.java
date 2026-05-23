package dio.marketplace.registration.infra.event;

import dio.marketplace.common.infra.event.dto.CustomerCreated;
import dio.marketplace.registration.domain.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RepositoryEventHandler
public class CustomerEventHandler {
    private final ApplicationEventPublisher eventPublisher;

    public CustomerEventHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @HandleAfterCreate
    public void handleCustomerCreated(Customer customer) {
        log.warn("Customer created: {}", customer);
        eventPublisher.publishEvent(new CustomerCreated(customer.getId().toString(), customer.getName()));
    }

    @HandleAfterSave
    public void handleCustomerUpdated(Customer customer) {
        log.warn("Customer updated: {}", customer);
    }

    @HandleAfterDelete
    public void handleCustomerDeleted(Customer customer) {
        log.warn("Customer deleted: {}", customer);
    }

}
