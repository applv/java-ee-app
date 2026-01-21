package com.applv.sobes.wildflyapp.resource.em;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import org.jboss.logging.Logger;

@ApplicationScoped
class WildFlyEntityManagerResourceProvider extends AbstractEntityManagerProvider {

  private final static Logger LOG = Logger.getLogger(WildFlyEntityManagerResourceProvider.class);

  private static final String IMPLEMENTATION_NAME = "wildfly.tutorDB";

  public WildFlyEntityManagerResourceProvider() {
    LOG.info("WildFlyEntityManagerResourceProvider instance created: " + this.hashCode());
  }

  @Getter
  @PersistenceContext(unitName = IMPLEMENTATION_NAME)
  private EntityManager em;

  @Override
  public String implementation() {
    return IMPLEMENTATION_NAME;
  }
}
