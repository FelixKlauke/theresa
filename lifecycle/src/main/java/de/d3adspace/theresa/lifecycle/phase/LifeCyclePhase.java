package de.d3adspace.theresa.lifecycle.phase;

/**
 * Describes the phases of the life cycle.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public enum LifeCyclePhase {

  /**
   * Describes that the life cycle is not yet starting or started.
   */
  LATENT,

  /**
   * Describes that the life cycle is currently warming up and starting.
   */
  STARTING,

  /**
   * Describes that the life cycle is active and running.
   */
  STARTED,

  /**
   * Describes that the life cycle is stopped.
   */
  STOPPED
}
