package eu.balev.kafka.demo.web;

import eu.balev.kafka.demo.service.KafkaDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaDemoController {

  private final KafkaDemoService kafkaDemoService;

  public KafkaDemoController(KafkaDemoService kafkaDemoService) {
    this.kafkaDemoService = kafkaDemoService;
  }

  @GetMapping("/create")
  public String create() {
    kafkaDemoService.createTestKafkaTopic();
    return "created";
  }

  @GetMapping("/publish")
  public String publish(@RequestParam("count") int count) {
    kafkaDemoService
        .publishMessages(count);
    return "published";
  }

  @GetMapping("/publish-with-key")
  public String publish(@RequestParam("key") String key) {
    kafkaDemoService
        .publishMessageWithKey(key);
    return "published";
  }
}
