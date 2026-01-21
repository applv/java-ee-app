package com.applv.sobes.wildflyapp.resource.em;

import static com.applv.sobes.wildflyapp.resource.ResourceConst.PERSISTENCE_UNIT_PARAM;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.TransactionScoped;
import java.util.stream.Collectors;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@ApplicationScoped
public class EntityManagerDispatcher {

  private final static Logger LOG = Logger.getLogger(EntityManagerDispatcher.class);

  @Inject
  @ConfigProperty(name = PERSISTENCE_UNIT_PARAM, defaultValue = "DEFAULT")
  private String impl;

  @Inject
  @Any
  private Instance<AbstractEntityManagerProvider> resources;

  @Produces
  @TransactionScoped
  public EntityManager produceEntityManager() {
    return resources
        .stream()
        .distinct()
        .peek(r -> LOG.info("EntityManager: " + r.get().toString()
            + "\n" + r.get().getProperties().entrySet().stream().map(e->e.getKey()+"-"+e.getValue()).collect(Collectors.joining("\n"))))
        .filter(res -> res.implementation().equalsIgnoreCase(impl))
        .findFirst()
        .map(AbstractEntityManagerProvider::get)
        .orElseThrow(() -> new RuntimeException("Unknown " + PERSISTENCE_UNIT_PARAM + ": " + impl));
  }
}
