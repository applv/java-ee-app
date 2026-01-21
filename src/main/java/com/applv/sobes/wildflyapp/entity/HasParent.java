package com.applv.sobes.wildflyapp.entity;

public interface HasParent {

  /**
   * Returns the parent of this entity.
   *
   * @return the parent entity, or null if there is no parent
   */
  HasParent getParent();
}
