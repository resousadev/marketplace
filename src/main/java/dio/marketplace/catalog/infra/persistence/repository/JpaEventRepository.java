package dio.marketplace.catalog.infra.persistence.repository;

import dio.marketplace.catalog.domain.Event;
import dio.marketplace.catalog.domain.EventId;
import dio.marketplace.catalog.domain.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class JpaEventRepository implements EventRepository {
    private final EventEntityRepository eventEntityRepository;

    public JpaEventRepository(EventEntityRepository eventRepository) {
        this.eventEntityRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        var iterable = eventEntityRepository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(JpaEventRepository::mapper)
                .toList();
    }

    private static Event mapper(dio.marketplace.catalog.infra.persistence.entity.Event entity) {
        return new Event(new EventId(entity.getId()), entity.getTitle(), entity.getDate(), Optional.empty());
    }

}
