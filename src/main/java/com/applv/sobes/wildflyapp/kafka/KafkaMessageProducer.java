package com.applv.sobes.wildflyapp.kafka;

public interface KafkaMessageProducer {

  void sendMessage(String topic, String key, String value);
}
