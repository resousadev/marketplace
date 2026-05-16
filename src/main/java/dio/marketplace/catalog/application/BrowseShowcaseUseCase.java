package dio.marketplace.catalog.application;

import dio.marketplace.catalog.domain.Event;
import dio.marketplace.catalog.domain.EventMetadataRepository;
import dio.marketplace.catalog.domain.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BrowseShowcaseUseCase {
  private final EventRepository eventRepository;
  private final EventEnricher eventEnricher;

  public BrowseShowcaseUseCase(EventRepository eventRepository, EventEnricher eventEnricher) {
    this.eventRepository = eventRepository;
    this.eventEnricher = eventEnricher;
  }

  public List<Event> execute() {
    var events = eventRepository.findAll()
        .stream()
        .map(eventEnricher::enrich).toList();

    return events;
  }
}
