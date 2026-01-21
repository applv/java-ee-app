package com.applv.sobes.wildflyapp.resource.em;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PostgresDbEntityManagerResourceProvider extends AbstractEntityManagerProvider {

  private final static Logger LOG = Logger.getLogger(PostgresDbEntityManagerResourceProvider.class);

  private static final String IMPLEMENTATION_NAME = "postgres.tutorDB";

  public PostgresDbEntityManagerResourceProvider() {
    LOG.info("PostgresDbEntityManagerResourceProvider instance created: " + this.hashCode());
  }

  @Getter
  @PersistenceContext(unitName=IMPLEMENTATION_NAME)
  private EntityManager em;

  @Override
  public String implementation() {
    return IMPLEMENTATION_NAME;
  }
}
