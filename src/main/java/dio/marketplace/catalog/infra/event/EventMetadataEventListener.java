package dio.marketplace.catalog.infra.event;

import dio.marketplace.catalog.infra.persistence.entity.EventMetadata;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventMetadataEventListener extends AbstractMongoEventListener<EventMetadata> {

    @Override
    public void onAfterSave(@NonNull AfterSaveEvent<EventMetadata> eventMetadata) {
        log.info("EventMetadata saved: {}", eventMetadata.getDocument());
    }

    @Override
    public void onAfterDelete(@NonNull AfterDeleteEvent<EventMetadata> eventMetadata) {
        log.info("EventMetadata deleted: {}", eventMetadata.getDocument());
    }
}
