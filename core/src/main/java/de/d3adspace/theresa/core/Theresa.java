package de.d3adspace.theresa.core;

import de.d3adspace.theresa.lifecycle.listener.LifeCycleTransactionListener;

/**
 * Central entrance class and core class of the theresa framework.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface Theresa {

    /**
     * Start the lifecycle management.
     */
    void startLifeCycle();

    /**
     * Provide the instance of the given class.
     *
     * @param instanceClass The instance class.
     */
    <T> T getInstance(Class<? extends T> instanceClass);

    /**
     * Stop the lifecycle management.
     */
    void stopLifeCycle();

    /**
     * Add a life cycle transaction listener.
     *
     * @param lifeCycleTransactionListener The listener.
     */
    void registerLifeCycleTransactionListener(LifeCycleTransactionListener lifeCycleTransactionListener);
}
