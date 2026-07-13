package com.frzlyv.transactions.shared.exceptions;

/**
 * UserAlreadyExistsException
 */
public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
