package dev.mauhux.apps.orders.business.api.exceptions;

public class ConflictException extends RuntimeException {

  private static final String DESCRIPTION = "Conflict Exception";

  public ConflictException(String detail) {
    super(DESCRIPTION + ". " + detail);
  }
}
