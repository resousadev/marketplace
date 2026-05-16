package dio.marketplace.catalog.application;

import dio.marketplace.catalog.domain.Event;
import dio.marketplace.catalog.domain.EventMetadataRepository;
import dio.marketplace.catalog.domain.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BrowseShowcaseUseCase {
  private final EventRepository eventRepository;
  private final EventMetadataRepository eventMetadataRepository;

  public BrowseShowcaseUseCase(EventRepository eventRepository, EventMetadataRepository eventMetadataRepository) {
    this.eventRepository = eventRepository;
    this.eventMetadataRepository = eventMetadataRepository;
  }

  public List<Event> execute() {
    var events = eventRepository.findAll()
        .stream()
        .map(event -> {
          log.info("Enriching event: {}", event);

          var metadata = eventMetadataRepository.findByEventId(event.getId());
          event.setMetadata(metadata);

          return event;
        }).toList();

    return events;
  }
}
