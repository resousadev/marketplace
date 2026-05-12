package dio.marketplace.catalog.infra.persistence.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Document(collection = "event_metadata")
@RequiredArgsConstructor
public class EventMetadata {

    @Id
    private String id;

    @NonNull
    private UUID eventId;

    private String description;

    private Map<String, Object> technicalRequirements;

    private List<Sector> sectors;

    private List<Seat> seats;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sector {
        private String name;
        private BigDecimal price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Seat {
        private String sectorName;
        private String code;
    }
}
