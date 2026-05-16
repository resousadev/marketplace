package dio.marketplace.catalog.application;

import dio.marketplace.catalog.application.dto.EventOutput;
import dio.marketplace.catalog.domain.Event;
import dio.marketplace.catalog.domain.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class BrowseShowcaseUseCase {
  private final EventRepository eventRepository;
  private final EventEnricher eventEnricher;

  public BrowseShowcaseUseCase(EventRepository eventRepository, EventEnricher eventEnricher) {
    this.eventRepository = eventRepository;
    this.eventEnricher = eventEnricher;
  }

  public List<EventOutput> execute() {
    var futures = eventRepository.findAll()
        .stream()
        .map(eventEnricher::enrich).toList();

    var events = futures.stream()
        .map(CompletableFuture::join)
        .map(EventOutput::fromEvent)
        .toList();

    log.info("{} events enriched", events.size());

    return events;
  }
}
