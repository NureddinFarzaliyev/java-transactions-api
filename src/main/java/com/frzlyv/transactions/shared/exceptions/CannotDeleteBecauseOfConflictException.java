package com.frzlyv.transactions.shared.exceptions;

/**
 * CannotDeleteBecauseOfConflictException
 */
public class CannotDeleteBecauseOfConflictException extends RuntimeException {
  public CannotDeleteBecauseOfConflictException(String message) {
    super(message);
  }
}
