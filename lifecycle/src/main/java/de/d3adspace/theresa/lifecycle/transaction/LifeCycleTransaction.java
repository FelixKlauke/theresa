package de.d3adspace.theresa.lifecycle.transaction;

import de.d3adspace.theresa.lifecycle.LifeCycle;
import de.d3adspace.theresa.lifecycle.state.LifeCycleState;

/**
 * Describes the context of a life cycle transaction by the instance, it's life cycle and the new state.
 *
 * @author Felix Klauke (info@felix-klauke.de)
 */
public class LifeCycleTransaction {

    private final Object instance;
    private final LifeCycle lifeCycle;
    private final LifeCycleState newLifeCycleState;

    public LifeCycleTransaction(Object instance, LifeCycle lifeCycle, LifeCycleState newLifeCycleState) {
        this.instance = instance;
        this.lifeCycle = lifeCycle;
        this.newLifeCycleState = newLifeCycleState;
    }

    public LifeCycleState getCurrentState() {

        return lifeCycle.getLifeCycleState();
    }

    public LifeCycleState getNewState() {

        return newLifeCycleState;
    }

    public Object getInstance() {

        return instance;
    }
}
