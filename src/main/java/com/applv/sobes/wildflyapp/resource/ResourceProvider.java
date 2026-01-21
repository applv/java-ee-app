package com.applv.sobes.wildflyapp.resource;

public interface ResourceProvider<T> {

  T get();

  String implementation();
}
