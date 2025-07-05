package dev.mauhux.apps.orders.business.api.exceptions;

public record ValidationError(String field, String message) {}