package de.d3adspace.theresa;

import com.google.inject.Injector;

/**
 * Central entrance class and core class of the theresa framework.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public interface Theresa extends Injector {

  /**
   * Start the lifecycle management.
   */
  void startLifeCycle();

  /**
   * Inject the member field of the given object.
   *
   * @param object The object.
   */
  void injectMembers(Object object);

  /**
   * Stop the lifecycle management.
   */
  void stopLifeCycle();
}
