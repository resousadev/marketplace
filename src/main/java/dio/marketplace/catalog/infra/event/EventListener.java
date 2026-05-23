package dio.marketplace.catalog.infra.event;

import dio.marketplace.catalog.infra.persistence.entity.Event;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventListener {

    @PostPersist
    public void onEventCreated(Event event) {
        log.info("Event created via @PostPersist: {}", event);
    }

    @PostUpdate
    public void onEventUpdated(Event event) {
        log.info("Event updated via @PostUpdate: {}", event);
    }

    @PostRemove
    public void onEventRemoved(Event event) {
        log.info("Event removed via @PostRemove: {}", event);
    }

}
