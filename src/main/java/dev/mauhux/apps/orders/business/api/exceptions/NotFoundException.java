package dev.mauhux.apps.orders.business.api.exceptions;

public class NotFoundException extends RuntimeException {

  private static final String DESCRIPTION = "Not Found Exception";

  public NotFoundException(String detail) {
    super(DESCRIPTION + ". " + detail);
  }
}
