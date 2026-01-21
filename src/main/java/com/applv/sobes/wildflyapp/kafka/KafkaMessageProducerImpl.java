package com.applv.sobes.wildflyapp.kafka;

import javax
    .enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

@ApplicationScoped
public class KafkaMessageProducerImpl implements KafkaMessageProducer {

  private final Producer<String, String> producer;

  @Inject
  public KafkaMessageProducerImpl(Producer<String, String> producer) {
    this.producer = producer;
  }

  @Override
  public void sendMessage(String topic, String key, String value) {
    producer.send(new ProducerRecord<>(topic, key, value));
  }
}
