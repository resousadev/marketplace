package dio.marketplace.ticketing.infra.event;

import dio.marketplace.common.infra.event.dto.CustomerCreated;
import dio.marketplace.common.infra.event.dto.EventUpdated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TicketingEventListener {

  @EventListener
  @Async
  public void handle(CustomerCreated event) {
    log.warn("CustomerCreated received: {}", event);
  }

  @EventListener
  @Async
  public void handle(EventUpdated event) {
    log.warn("EventUpdated received: {}", event);
  }

}
