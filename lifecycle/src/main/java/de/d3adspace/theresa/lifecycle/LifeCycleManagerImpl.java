package de.d3adspace.theresa.lifecycle;

import com.google.common.collect.Maps;
import de.d3adspace.theresa.lifecycle.phase.LifeCycleManagerPhase;
import de.d3adspace.theresa.lifecycle.state.LifeCycleState;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The default implementation of the {@link LifeCycleManager}.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class LifeCycleManagerImpl implements LifeCycleManager {

    /**
     * All instances we are currently managing.
     */
    private final Map<Object, LifeCycle> managedInstances = Maps.newConcurrentMap();

    /**
     * The reference to the life cycle manager phase.
     */
    private final AtomicReference<LifeCycleManagerPhase> phaseReference = new AtomicReference<>(LifeCycleManagerPhase.LATENT);

    @Override
    public void manageInstance(Object instance) {

        // Create life cycle and let it build up callbacks
        LifeCycle lifeCycle = createLifeCycle(instance);

        // Save the life cycle instance
        managedInstances.put(instance, lifeCycle);

        // Start instance life cycle
        lifeCycle.processLifeCycleStateChange(LifeCycleState.CONSTRUCTION);
    }

    @Override
    public void startLifeCycle() {

        // Check if we even can start the life cycle management from here
        boolean validPhase = phaseReference.compareAndSet(LifeCycleManagerPhase.LATENT, LifeCycleManagerPhase.STARTING);
        if (!validPhase) {
            throw new IllegalStateException("Can't start life cycle from phase " + phaseReference.get());
        }

        // Setup is complete, let the running phase begin
        phaseReference.set(LifeCycleManagerPhase.STARTED);
    }

    @Override
    public void stopLifeCycle() {

        // Set life cycle states to destruction and clean up objects
        for (LifeCycle value : managedInstances.values()) {
            value.processLifeCycleStateChange(LifeCycleState.DESTRUCTION);
        }

        // We don't need the instances anymore
        managedInstances.clear();
    }

    /**
     * Create a life cycle wrapper for the given instance.
     *
     * @param instance The instance.
     *
     * @return The life cycle wrapper instance.
     */
    private LifeCycle createLifeCycle(Object instance) {

        // Prohibit new objects when the life cycle is already stopped
        if (phaseReference.get() == LifeCycleManagerPhase.STOPPED) {
            throw new IllegalStateException("Can't take new instances as life cycle management is stopped.");
        }

        // Construct life cycle, initial state is none and will be modified later if the life cycle is active
        return new LifeCycle(instance, LifeCycleState.NONE);
    }
}
