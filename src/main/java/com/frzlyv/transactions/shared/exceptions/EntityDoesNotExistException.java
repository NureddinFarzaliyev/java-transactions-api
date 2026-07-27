package com.frzlyv.transactions.shared.exceptions;

/**
 * EntityDoesNotExistException
 */
public class EntityDoesNotExistException extends RuntimeException {
  public EntityDoesNotExistException(String message) {
    super(message);
  }
}
