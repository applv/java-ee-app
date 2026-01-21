package com.applv.sobes.wildflyapp.resource.kafka;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import com.applv.sobes.wildflyapp.resource.ResourceProvider;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class ApacheKafkaProducerResourceProvider implements ResourceProvider<Producer<String, String>> {

  private final static String IMPLEMENTATION_NAME = "kafka-clients-4.1.1:org.apache.kafka.clients.producer.KafkaProducer";
  Producer<String, String> producer;

  @Inject
  @ConfigProperty(name = BOOTSTRAP_SERVERS_CONFIG)
  private String kafkaBootstrapServers;

  @Inject
  @ConfigProperty(name = KEY_SERIALIZER_CLASS_CONFIG)
  private String kafkaKeySerializer;

  @Inject
  @ConfigProperty(name = VALUE_SERIALIZER_CLASS_CONFIG)
  private String kafkaValueSerializer;

  @PostConstruct
  public void initConfig() {
    Properties props = new Properties();
    props.put(BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
    props.put(KEY_SERIALIZER_CLASS_CONFIG, kafkaKeySerializer);
    props.put(VALUE_SERIALIZER_CLASS_CONFIG, kafkaValueSerializer);

    producer = new KafkaProducer<>(props);
  }

  public Producer<String, String> get() {
    return producer;
  }

  @Override
  public String implementation() {
    return IMPLEMENTATION_NAME;
  }
}
