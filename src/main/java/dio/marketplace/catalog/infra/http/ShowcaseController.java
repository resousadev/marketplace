package dio.marketplace.catalog.infra.http;

import dio.marketplace.catalog.application.BrowseShowcaseUseCase;
import dio.marketplace.catalog.application.dto.EventOutput;
import dio.marketplace.catalog.domain.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/showcase")
public class ShowcaseController {
  private final BrowseShowcaseUseCase showcaseService;

  public ShowcaseController(BrowseShowcaseUseCase showcaseService) {
    this.showcaseService = showcaseService;
  }

  @GetMapping
  public List<EventOutput> getShowcase() {
    return showcaseService.execute();
  }

}
