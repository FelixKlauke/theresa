package de.d3adspace.theresa.lifecycle;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public interface LifeCycleManager {

    /**
     * Let the life cycle manager manage the given instance.
     *
     * @param instance The instance.
     */
    void manageInstance(Object instance);

    /**
     * Let the life cycle management begin.
     */
    void startLifeCycle();

    /**
     * Stop the life cycle management.
     */
    void stopLifeCycle();
}
