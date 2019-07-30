package de.d3adspace.theresa.lifecycle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.d3adspace.theresa.lifecycle.listener.LifeCycleTransactionListener;
import de.d3adspace.theresa.lifecycle.phase.LifeCycleManagerPhase;
import de.d3adspace.theresa.lifecycle.state.LifeCycleState;
import de.d3adspace.theresa.lifecycle.transaction.LifeCycleTransaction;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The default implementation of the {@link LifeCycleManager}.
 *
 * @author Felix Klauke (info@felix-klauke.de)
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

    /**
     * List with all currently known life cycle state listeners.
     */
    private final List<LifeCycleTransactionListener> lifeCycleTransactionListeners = Lists.newCopyOnWriteArrayList();

    @Override
    public void manageInstance(Object instance) {

        // Create life cycle and let it build up callbacks
        LifeCycle lifeCycle = createLifeCycle(instance);

        // Save the life cycle instance
        managedInstances.put(instance, lifeCycle);

        // Start instance life cycle
        processLifeCycleTransaction(instance, lifeCycle, LifeCycleState.CONSTRUCTION);
    }

    @Override
    public void startLifeCycle() {

        // Check if we even can start the life cycle management from here
        boolean validPhase = phaseReference.compareAndSet(LifeCycleManagerPhase.LATENT, LifeCycleManagerPhase.STARTING);
        if (!validPhase) {
            throw new IllegalStateException("Can't start life cycle from phase " + phaseReference.get());
        }

        // Process warm up state
        managedInstances.values().forEach(lifeCycle -> {

            processLifeCycleTransaction(lifeCycle.getHandle(), lifeCycle, LifeCycleState.WARM_UP);
        });

        // Setup is complete, let the running phase begin
        phaseReference.set(LifeCycleManagerPhase.STARTED);
    }

    @Override
    public void stopLifeCycle() {

        // Check if life cycle has already started
        if (phaseReference.get() != LifeCycleManagerPhase.STARTED) {
            throw new IllegalStateException("You can't stop a not started life cycle.");
        }

        // Set life cycle states to destruction and clean up objects
        for (LifeCycle value : managedInstances.values()) {

            processLifeCycleTransaction(value.getHandle(), value, LifeCycleState.DESTRUCTION);
        }

        // We don't need the instances anymore
        managedInstances.clear();

        // Update life cycle phase to stopped
        phaseReference.set(LifeCycleManagerPhase.STOPPED);
    }

    @Override
    public void registerLifeCycleTransactionListener(LifeCycleTransactionListener lifeCycleTransactionListener) {

        lifeCycleTransactionListeners.add(lifeCycleTransactionListener);
    }

    /**
     * Process the transaction of an object into a new life cycle state.
     *
     * @param lifeCycle      The life cycle.
     * @param lifeCycleState The new state.
     */
    private void processLifeCycleTransaction(Object instance, LifeCycle lifeCycle, LifeCycleState lifeCycleState) {

        // Create transaction and fire them through listener
        LifeCycleTransaction lifeCycleTransaction = new LifeCycleTransaction(instance, lifeCycle, lifeCycleState);
        for (LifeCycleTransactionListener transactionListener : lifeCycleTransactionListeners) {

            transactionListener.onLifeCycleTransaction(lifeCycleTransaction);
        }

        // Delegate change to the life cycle and let it take action.
        lifeCycle.processLifeCycleStateChange(lifeCycleState);
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
