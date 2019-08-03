package de.d3adspace.theresa.lifecycle.phase;

/**
 * Describes the phases of the life cycle manager.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public enum LifeCycleManagerPhase {

  /**
   * Describes that the life cycle manager is not yet starting or started.
   */
  LATENT,

  /**
   * Describes that the life cycle manager is currently warming up and starting.
   */
  STARTING,

  /**
   * Describes that the life cycle manager is active and running.
   */
  STARTED,

  /**
   * Describes that the life cycle manager is stopped.
   */
  STOPPED
}
