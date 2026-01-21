package com.applv.sobes.wildflyapp.resource.kafka;

import static com.applv.sobes.wildflyapp.resource.ResourceConst.KAFKA_IMPLEMENTATION_PARAM;

import com.applv.sobes.wildflyapp.resource.ResourceProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import org.apache.kafka.clients.producer.Producer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class KafkaProducerDispatcher {

  @Inject
  @ConfigProperty(name = KAFKA_IMPLEMENTATION_PARAM, defaultValue = "DEFAULT")
  private String impl;

  @Inject
  @Any
  private Instance<ResourceProvider<Producer<String, String>>> resources;

  @ApplicationScoped
  @Produces
  public Producer<String, String> get() {
    return resources
        .stream()
        .filter(r -> r.implementation().equals(impl))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("An implementation of KafkaProducer is not defined."))
        .get();
  }

  public void close(@Disposes Producer<String, String> producer) {
    producer.close();
  }
}
