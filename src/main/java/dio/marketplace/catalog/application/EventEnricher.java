package dio.marketplace.catalog.application;

import dio.marketplace.catalog.domain.Event;
import dio.marketplace.catalog.domain.EventMetadataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class EventEnricher {
  private final EventMetadataRepository eventMetadataRepository;

  public EventEnricher(EventMetadataRepository eventMetadataRepository) {
    this.eventMetadataRepository = eventMetadataRepository;
  }

  @Async
  public CompletableFuture<Event> enrich(Event event) {
    log.info("Enriching event: {}", event);

    var metadata = eventMetadataRepository.findByEventId(event.getId());
    event.setMetadata(metadata);

    return CompletableFuture.completedFuture(event);
  }

}
