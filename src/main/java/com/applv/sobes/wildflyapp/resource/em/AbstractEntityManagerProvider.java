package com.applv.sobes.wildflyapp.resource.em;

import com.applv.sobes.wildflyapp.resource.ResourceProvider;
import javax.persistence.EntityManager;

public abstract class AbstractEntityManagerProvider implements ResourceProvider<EntityManager> {

  protected abstract EntityManager getEm();

  @Override
  public EntityManager get() {
    return getEm();
  }
}
