package de.d3adspace.theresa.lifecycle.exception;

import com.google.common.base.Preconditions;

public final class LifeCycleCallbackException extends RuntimeException {

  private LifeCycleCallbackException(String message, Throwable cause) {
    super(message, cause);
  }

  public static LifeCycleCallbackException withMessageAndCause(String message, Throwable cause) {
    Preconditions.checkNotNull(message, "Message should not be null");
    Preconditions.checkNotNull(cause, "Cause should not be null");
    return new LifeCycleCallbackException(message, cause);
  }
}
