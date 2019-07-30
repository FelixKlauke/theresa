package de.d3adspace.theresa.lifecycle.state;

/**
 * Describes all states an instance's life cycle can have and go through.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public enum LifeCycleState {

    /**
     * NONE state is when the instance is in life cycle setup.
     */
    NONE,

    /**
     * Construction state is when the instance gets created.
     */
    CONSTRUCTION,

    /**
     * Warmup state is when the life cycle manager is started.
     */
    WARM_UP,

    /**
     * Destruction state is when the instance is cleaned up.
     */
    DESTRUCTION
}
