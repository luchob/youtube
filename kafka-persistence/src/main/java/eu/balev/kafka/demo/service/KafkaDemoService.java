package eu.balev.kafka.demo.service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaDemoService {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDemoService.class);

  private static final String ALPHANUMERICAL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  private static final String KAFKA_TEST_TOPIC = "test-topic";
  private static final int KILOBYTE = 1000;

  private final KafkaAdmin kafkaAdmin;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaDemoService(KafkaAdmin kafkaAdmin,
                          KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaAdmin = kafkaAdmin;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void createTestKafkaTopic() {

    try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {

      Set<String> topics = adminClient.listTopics().names().get();

      if (topics.contains(KAFKA_TEST_TOPIC)) {
        LOGGER.info("Topic {} already exists.", KAFKA_TEST_TOPIC);
        return;
      }

      Map<String, String> configs = new HashMap<>();

      configs.put("segment.bytes", String.valueOf(10 * KILOBYTE));
      configs.put("cleanup.policy", "compact");

      NewTopic newTopic = new NewTopic(KAFKA_TEST_TOPIC, 1, (short) 1).configs(configs);

      adminClient.createTopics(Collections.singletonList(newTopic)).all().get();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  public void publishMessages(int count) {

    List<CompletableFuture<SendResult<String, String>>> all = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      try {
        CompletableFuture<SendResult<String, String>> result =
            kafkaTemplate.send(KAFKA_TEST_TOPIC, UUID.randomUUID().toString(), createNonRandomMessage());

        all.add(result);

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      LOGGER.info("Published message: {}", i);
    }

    all.forEach(cf -> {
      try {
        cf.get();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
  }

  public void publishMessageWithKey(String key) {

    try {
      kafkaTemplate.send(KAFKA_TEST_TOPIC, key, createNonRandomMessage()).get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    LOGGER.info("Published message with key: {}", key);
  }

  public static String createRandomMessage() {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    while (sb.toString().getBytes(StandardCharsets.UTF_8).length < 64) {
      int index = random.nextInt(ALPHANUMERICAL_CHARACTERS.length());
      sb.append(ALPHANUMERICAL_CHARACTERS.charAt(index));
    }
    return sb.toString();
  }

  public static String createNonRandomMessage() {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();

    int index = random.nextInt(ALPHANUMERICAL_CHARACTERS.length());
    sb.append(ALPHANUMERICAL_CHARACTERS.charAt(index));

    while (sb.toString().getBytes(StandardCharsets.UTF_8).length < 63) {
      sb.append(ALPHANUMERICAL_CHARACTERS.charAt(index));
    }
    return sb.toString();
  }


//      configs.put("cleanup.policy", "delete");
//      configs.put("retention.ms", "10000");
}
