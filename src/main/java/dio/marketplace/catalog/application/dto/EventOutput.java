package dio.marketplace.catalog.application.dto;

import dio.marketplace.catalog.domain.Event;
import dio.marketplace.catalog.domain.EventMetadata;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record EventOutput (
    String id,
    String title,
    Instant date,
    EventMetadataOutput metadata
) {

  public record EventMetadataOutput(
      String eventDescription,
      Map<String, Object> technicalRequirements,
      Map<String, List<SeatOutput>> seatsBySector
  ) {
    public record SeatOutput(
        String id,
        String sectorId,
        BigDecimal price
    ) {
    }
  }

  public static EventMetadataOutput fromEventMetadata(EventMetadata metadata) {
    Map<String, List<EventMetadataOutput.SeatOutput>> seats =
        metadata.seats().entrySet().stream()
            .collect(Collectors.toMap(
                enty -> enty.getKey().getId().name(),
                enty -> enty.getValue().stream()
                    .map(seat -> new EventMetadataOutput.SeatOutput(
                        seat.getId().seatNumber(),
                        seat.getSectorId().name(),
                        enty.getKey().getPrice()
                    ))
                    .toList()
            ));
    return new EventOutput.EventMetadataOutput(
        metadata.eventDescription(),
        metadata.technicalRequirements(),
        seats
    );
  }

  public static EventOutput fromEvent(Event event) {
    return new EventOutput(
        event.getId().id().toString(),
        event.getTitle(),
        event.getDate(),
        event.getMetadata()
            .map(EventOutput::fromEventMetadata)
            .orElse(null)
    );
  }
}
